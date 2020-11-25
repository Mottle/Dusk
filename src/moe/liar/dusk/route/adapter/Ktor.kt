package moe.liar.dusk.route.adapter

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.*
import kotlinx.coroutines.Deferred
import moe.liar.dusk.route.*
import moe.liar.dusk.route.HttpStatusCode
import moe.liar.dusk.utils.MultiMap
import moe.liar.dusk.utils.add
import moe.liar.dusk.utils.multiMapOf

class KtorContext(override val request: RequestContext,
                  override val response: ResponseContext) : Context

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
}.fold(multiMapOf<String, String>()) { mp, entry ->
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

    override suspend fun respond( buffer: ByteArray, statusCode: HttpStatusCode, contentType: HttpContentType) {
        val contents = contentType.value.split("/")
        val type = contents.first()
        val subType = contents[1]
        call.respondBytes(buffer, ContentType(type, subType), io.ktor.http.HttpStatusCode(statusCode.code, statusCode.info))
    }
}