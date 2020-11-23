package moe.liar.dusk.route.adapter

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.util.*
import kotlinx.coroutines.Deferred
import moe.liar.dusk.route.*
import moe.liar.dusk.utils.Multimap
import moe.liar.dusk.utils.multimapOf

class KtorContext(override val request: RequestContext,
                  override val response: ResponseContext) : Context

class KtorRequestContext(private val call: ApplicationCall) : RequestContext {
    override fun uri(): String = call.request.uri

    override fun headers(): Multimap<String, String> = call.request.headers.toMap().map { entry ->
        val mutableSet = mutableSetOf<String>()
        entry.value.forEach {
            mutableSet.add(it)
        }
        return@map Pair(entry.key, mutableSet)
    }.fold(multimapOf()) { mp, entry ->
        mp[entry.first] = entry.second
        return@fold mp
    }
}

class KtorResponseContext(private val call: ApplicationCall) : ResponseContext {
    override fun headers(): Multimap<String, String> {
        TODO("Not yet implemented")
    }

    override fun header(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override fun status(): HttpStatusCode {
        TODO("Not yet implemented")
    }

    override fun status(code: HttpStatusCode) {
        TODO("Not yet implemented")
    }

    override fun respondAsync(buffer: ByteArray, contentType: HttpContentType): Deferred<Unit> {
        TODO("Not yet implemented")
    }
}