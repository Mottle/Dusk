package moe.liar.dusk.route

import kotlinx.coroutines.Deferred
import moe.liar.dusk.utils.Multimap

interface Context {
    val request: RequestContext
    val response: ResponseContext
}

interface RequestContext {
    fun uri(): String
    fun headers(): Multimap<String, String>
}

interface ResponseContext {
    fun headers(): Multimap<String, String>
    fun header(key: String, value: String)
    fun status(): HttpStatusCode
    fun status(code: HttpStatusCode)
    fun respondAsync(buffer: ByteArray, contentType: HttpContentType): Deferred<Unit>
}