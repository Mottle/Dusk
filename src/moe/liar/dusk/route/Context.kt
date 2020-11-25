package moe.liar.dusk.route

import kotlinx.coroutines.Deferred
import moe.liar.dusk.utils.MultiMap

interface Context {
    val request: RequestContext
    val response: ResponseContext
}

interface RequestContext {
    fun uri(): String
    fun headers(): MultiMap<String, String>
}

interface ResponseContext {
    fun headers(): MultiMap<String, String>
    fun header(key: String, value: String)
    fun status(): HttpStatusCode
    fun status(code: HttpStatusCode)
    suspend fun respond(buffer: ByteArray, statusCode: HttpStatusCode, contentType: HttpContentType)
}