package moe.liar

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.css.article
import moe.liar.handler.ArticleHandler
import moe.liar.handler.IndexHandler
import moe.liar.page.*
import moe.liar.utils.some
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Locations)
    install(AutoHeadResponse)
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respondRedirect("/static/html/404.html")
        }
    }
    val layout = MainLayout("/static".some(), listOf(), listOf("animation.css"))
    routing {
        get<IndexHandler> { handler ->
            val page = handler.handle()
            call.respondHtml {
                layout.build(this, page)
            }
        }

        get<ArticleHandler> { handler ->
            val page = handler.handle()
            call.respondHtml {
                layout.build(this, page)
            }
        }

        static("/static") {
            resources("static")
        }
        get("/favicon.ico") {
            log.info("log for ic")
            call.respondRedirect("/static/favicon.png")
        }

    }
}