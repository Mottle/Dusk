package moe.liar.dusk.route

interface Router {
    suspend fun route(method: HttpMethod, path: String, callback: suspend (Context).() -> Unit)
}

