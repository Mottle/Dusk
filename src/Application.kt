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
import kotlinx.css.CSSBuilder
import moe.liar.page.MainLayout
import moe.liar.page.NavBar
import moe.liar.page.build
import moe.liar.utils.none
import moe.liar.utils.some
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Locations) { }
    install(AutoHeadResponse)
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respond(
                TextContent(
                    "${it.value} ${it.description}",
                    ContentType.Text.Plain.withCharset(Charsets.UTF_8),
                    it
                )
            )
        }
    }
    val layout = MainLayout("/static".some(), none(), none())
    routing {
        get("/") {
            call.respondHtml {
                layout.build(this, ::NavBar)
            }
        }
        get("/favicon.ico") {
            call.respondRedirect("/static/favicon.png")
        }
        static("/static") {
            resources("static")
        }
    }
}