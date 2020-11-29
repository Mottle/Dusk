package moe.liar.dusk.route

import kotlinx.html.HTML
import moe.liar.dusk.utils.MultiMap
import moe.liar.dusk.utils.html

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

suspend fun ResponseContext.respondHtml(fn: (HTML).() -> Unit) {
    val htmlText = html(fn)
    respond(htmlText.toByteArray(), HttpStatusCode.Ok, HttpContentType.Html)
}