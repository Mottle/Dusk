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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moe.liar.handler.AboutMeHandler
import moe.liar.handler.ArticleHandler
import moe.liar.handler.IndexHandler
import moe.liar.handler.StatusPageHandler
import moe.liar.model.ArticleDAO
import moe.liar.model.LocalRandomBackground
import moe.liar.page.MainLayout
import moe.liar.page.build
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
        HttpStatusCode.allStatusCodes.filter { it.value - 400 >= 0 }.forEach { code ->
            status(code) {
                call.respondRedirect("/status/${code.value}")
            }
        }
    }
    GlobalScope.launch {
        ArticleDAO.refresh()
        LocalRandomBackground.precache()
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

        get<StatusPageHandler> { handler ->
            val page = handler.handle()
            call.respondHtml {
                layout.build(this, page)
            }
        }

        get<AboutMeHandler> { handler ->
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