package moe.liar.dusk

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moe.liar.dusk.component.Component
import moe.liar.dusk.component.MainLayout
import moe.liar.dusk.handler.*
import moe.liar.dusk.model.ArticleDAO
import moe.liar.dusk.model.LocalRandomBackground
import moe.liar.dusk.route.Context
import moe.liar.dusk.route.adapter.KtorRouter
import moe.liar.dusk.route.adapter.param
import moe.liar.dusk.route.adapter.static
import moe.liar.dusk.route.adapter.redirect
import moe.liar.dusk.route.get
import moe.liar.dusk.route.respondHtml
import moe.liar.dusk.utils.getOrElse
import moe.liar.dusk.utils.some
import org.slf4j.event.Level
import java.io.File

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
        ArticleDAO.refreshAsync().await()
        LocalRandomBackground.precacheAsync().await()
    }

    val router = KtorRouter(this)
    val layout = MainLayout("/static".some(), listOf(), listOf("animation.css"))
    router.static("/static", "./web-resource/static/")
    GlobalScope.launch(Dispatchers.IO) {
        with(router) {
            get("/") {
                handleRespond(layout, handleIndex())
            }

            get("/article/{id}") {
                val id = request.param("id").forceGet().toInt()
                handleRespond(layout, handleArticle(id))
            }

            get("/status/{code}") {
                val code = request.param("code").getOrElse("500").toInt()
                handleRespond(layout, handleStatus(code))
            }

            get("/about") {
                handleRespond(layout, handleAbout())
            }

            get("/favicon.ico") {
                response.redirect("/static/favicon.png")
            }
        }
    }
}

suspend fun Context.handleRespond(layout: MainLayout, component: Component) = response.respondHtml {
    layout(this, component)
}