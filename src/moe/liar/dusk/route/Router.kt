package moe.liar.dusk.route

interface Router {
    suspend fun route(method: HttpMethod, path: String, callback: suspend (Context).() -> Unit)
}

suspend fun Router.get(path: String, callback: suspend Context.() -> Unit) = route(HttpMethod.GET, path, callback)
suspend fun Router.post(path: String, callback: suspend Context.() -> Unit) = route(HttpMethod.POST, path, callback)
suspend fun Router.put(path: String, callback: suspend Context.() -> Unit) = route(HttpMethod.PUT, path, callback)

