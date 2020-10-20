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
        div("d-none d-lg-block") {
            nav("navbar navbar-expand-lg navbar-light bg-light fix-top top-bar") {
                id = "top-bar"
                nav(this)
            }
        }
        div("d-lg-none") {
            nav("navbar navbar-expand-lg navbar-light bg-light fix-top top-bar") {
                nav(this)
            }
        }

        style(type = ContentType.Text.CSS.toString()) {
            css {
                rule(".top-bar") {
                    zIndex = 1000
                    fontSize = 20.px
                    width = 100.pct
                    height = LinearDimension.auto
                    opacity = 0.8
                }

                rule(".topbar-brand") {
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

    private fun nav(nv: NAV) = with(nv) {
        a(href = "/", classes = "navbar-brand topbar-brand") {
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
            attributes["data-target"] = "#navbarColor01"
            attributes["aria-controls"] = "navbarColor01"
            attributes["aria-expanded"] = "false"
            attributes["aria-label"] = "Toggle navigation"
            span("navbar-toggler-icon")
        }
        div("collapse navbar-collapse") {
            id = "navbarColor01"
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
}