package moe.liar.dusk.component

import io.ktor.http.*
import kotlinx.css.LinearDimension
import kotlinx.css.pct
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.css.px
import kotlinx.html.*
import moe.liar.dusk.model.JsRes
import moe.liar.dusk.model.Resources
import moe.liar.dusk.utils.*
import moe.liar.utils.*

class NavBar private constructor(private val logo: Option<Resources>, private val forceShow: Boolean) : Component {
    data class Builder(private val logo: Option<Resources> = none(), private val forceShow: Boolean = false) {
        fun setLogo(lg: Resources) = copy(logo = lg.some())
        fun setForceShown() = copy(forceShow = true)
        fun build() = NavBar(logo, forceShow)
    }

    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) {
        htmlBody.header {
            bodyHeader(this)
        }
    }

    private fun bodyHeader(header: HEADER) = with(header) {
        div("d-none d-lg-block") {
            nav("navbar navbar-expand-lg navbar-light bg-light fix-top top-bar") {
                if (forceShow) attributes["style"] = "opacity: 1"
                id = "top-bar"
                nav(this)
            }
        }
        div("d-lg-none") {
            nav("navbar navbar-expand-lg navbar-light bg-light fix-top top-bar") {
                if (forceShow) attributes["style"] = "opacity: 1"
                nav(this)
            }
        }

        style(type = ContentType.Text.CSS.toString()) {
            css {
                rule(".top-bar") {
                    zIndex = 1000
                    fontSize = LinearDimension("1rem")
                    width = 100.pct
                    height = LinearDimension.auto
                    opacity = 0.8
                    transition(property = "opacity", duration = 0.5.s)
                }

                rule("#top-bar") {
                    opacity = 0
                    transition(property = "opacity", duration = 0.5.s)
                }

                rule(".topbar-brand") {
                    fontSize = LinearDimension("1.4rem")
                }

                rule(".fix-top") {
                    top = 0.px
                    left = 0.px
                }

                rule("#top-bar:hover") {
                    opacity = 0.8
                }
            }
            unsafe {
                +".fix-top { position: fixed !important }"
                +".force-show { opacity: 1 !important }"
            }
        }

    }

    override fun script(htmlBody: BODY): Unit = with(htmlBody) {
        script(src = JsRes.path("navbar.js").uri()) {}
    }

    private fun nav(nv: NAV) = with(nv) {
        a(href = "/", classes = "navbar-brand topbar-brand") {
            logo.map {
                img(src = it.uri()) {
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
                    a(classes = "nav-link", href = "/") {
                        +"Home"
                    }
                }
                li("nav-item") {
                    a(classes = "nav-link", href = "/about") {
                        +"about"
                    }
                }
            }
        }
    }
}