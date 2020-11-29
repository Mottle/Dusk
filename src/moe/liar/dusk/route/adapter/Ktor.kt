package moe.liar.dusk.route.adapter

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.get
import io.ktor.util.*
import io.ktor.util.pipeline.*
import moe.liar.dusk.route.*
import moe.liar.dusk.route.HttpMethod
import moe.liar.dusk.route.HttpStatusCode
import moe.liar.dusk.utils.MultiMap
import moe.liar.dusk.utils.add
import moe.liar.dusk.utils.multiMapOf
import java.io.File
import kotlin.collections.first
import kotlin.collections.fold
import kotlin.collections.forEach
import kotlin.collections.map
import kotlin.collections.mutableSetOf
import kotlin.collections.set

class KtorContext(call: ApplicationCall) : Context {
    override val request: RequestContext = KtorRequestContext(call)
    override val response: ResponseContext = KtorResponseContext(call)
}

class KtorRequestContext(private val call: ApplicationCall) : RequestContext {
    override fun uri(): String = call.request.uri

    override fun headers(): MultiMap<String, String> = call.request.headers.toMultiMap()
}

internal fun Headers.toMultiMap(): MultiMap<String, String> = toMap().map { entry ->
    val mutableSet = mutableSetOf<String>()
    entry.value.forEach {
        mutableSet.add(it)
    }
    return@map Pair(entry.key, mutableSet)
}.fold(multiMapOf()) { mp, entry ->
    mp[entry.first] = entry.second
    return@fold mp
}

class KtorResponseContext(private val call: ApplicationCall) : ResponseContext {
    private var headers = multiMapOf<String, String>()
    private var statusCode = HttpStatusCode.Ok
    override fun headers(): MultiMap<String, String> = headers

    override fun header(key: String, value: String) {
        headers.add(key, value)
    }

    override fun status(): HttpStatusCode = statusCode

    override fun status(code: HttpStatusCode) {
        statusCode = code
    }

    override suspend fun respond(buffer: ByteArray, statusCode: HttpStatusCode, contentType: HttpContentType) {
        val contents = contentType.value.split("/")
        val type = contents.first()
        val subType = contents[1]
        call.respondBytes(
            buffer,
            ContentType(type, subType),
            io.ktor.http.HttpStatusCode(statusCode.code, statusCode.info)
        )
    }
}

class KtorRouter(app: Application) : Router {
    val ktorRouter = app.routing {}
    override suspend fun route(method: HttpMethod, path: String, callback: suspend Context.() -> Unit) {
        val met: (String, PipelineInterceptor<Unit, ApplicationCall>) -> Route = when (method) {
            HttpMethod.GET -> ktorRouter::get
            HttpMethod.POST -> ktorRouter::post
            HttpMethod.PUT -> ktorRouter::put
            HttpMethod.DELETE -> ktorRouter::delete
            HttpMethod.HEAD -> ktorRouter::head
            HttpMethod.OPTIONS -> ktorRouter::options
            HttpMethod.PATCH -> ktorRouter::patch
            else -> throw RuntimeException("ktor router do not support ${method.name}")
        }
        met(path) {
            val context = KtorContext(call)
            callback(context)
        }
    }
}

fun Router.static(path: String) {
    this as KtorRouter
    this.ktorRouter.static {
        staticRootFolder = File(path)
        files(".")
    }
}