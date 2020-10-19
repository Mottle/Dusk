package moe.liar.page

import io.ktor.http.*
import kotlinx.css.*
import kotlinx.html.*
import moe.liar.utils.*

class NavBar(override val static: Option<String> = none()) : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) {
        htmlBody.header {
            bodyHeader(this)
        }
    }

    private fun bodyHeader(header: HEADER) = with(header) {
        val `navbar-toggler` = "navbarColor01"
        nav("navbar navbar-expand-lg navbar-light bg-light fix-top") {
            id = "top-bar"
            a(href = "/", classes = "navbar-brand") {
                id = "brand"
                static.map {
                    img(src = "$it/logo.png") {
                        width = "30px"
                        height = "30px"
                    }
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
            css {
                rule("#top-bar") {
                    fontSize = 20.px
                    width = 100.pct
                    height = LinearDimension.auto
                    opacity = 1
                }

                rule("#brand") {
                    fontSize = 25.px
                }

                rule(".fix-top") {
                    top = 0.px
                    left = 0.px
                }
            }
            unsafe {
                +".fix-top {position: fixed !important}"
            }
        }
    }

    override fun script(htmlBody: BODY): Unit = with(htmlBody) {
        static.map {
            script(src = "$it/js/navbar.js") {}
        }
    }
}