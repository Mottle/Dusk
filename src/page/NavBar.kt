package moe.liar.page

import io.ktor.http.*
import kotlinx.css.CSSBuilder
import kotlinx.css.px
import kotlinx.html.*
import moe.liar.utils.Option
import moe.liar.utils.flatMap
import moe.liar.utils.none
import moe.liar.utils.some
import org.slf4j.Logger

class NavBar(override val static: Option<String> = none()) : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) {
        htmlBody.header {
            bodyHeader(this)
        }
    }

    private fun bodyHeader(header: HEADER) = with(header) {
        val `navbar-toggler` = "navbarColor03"
        div("navbar navbar-expand-lg navbar-light bg-light top-bar") {
            a(href = "/", classes = "navbar-brand") {
                static.flatMap {
                    img(src = "$it/logo.png") {
                        width = "30px"
                        height = "30px"
                    }.some()
                }
                +"Outside"
            }
            button(classes = "navbar-toggler", type = ButtonType.button) {
                attributes["data-toggle"] = "collapse"
                attributes["data-target"] = "#${`navbar-toggler`}"
                attributes["aria-controls"] = `navbar-toggler`
                attributes["aria-expanded"] = "false"
                attributes["aria-label"] = "Toggle navigation"
                span("navbar-toggler-icon")
            }
            div("collapse navbar-collapse") {
                id = `navbar-toggler`
                ul("navbar-nav mr-aut") {
                    li("nav-item") {
                        a(classes = "nav-link", href = "#") {
                            +"Home"
                        }
                    }
                    li("nav-item") {
                        a(classes = "nav-link", href = "#") {
                            +"about"
                        }
                    }
                }
            }
        }
        style(type = ContentType.Text.CSS.toString()) {
//            val css = CSSBuilder()
//            with(CSSBuilder()) {
//                rule(".top-bar") {
//                    fontSize = 15.px
//                }
//            }
//            println(css.toString())
//            unsafe {
//                +css.toString()
//            }
            unsafe {
                +"""
                    .top-bar { font-size:15px; }
                """.trimIndent()
            }
        }
    }

    override fun script(htmlBody: BODY) {
        TODO("Not yet implemented")
    }
}