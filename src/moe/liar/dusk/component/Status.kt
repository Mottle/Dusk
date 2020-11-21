package moe.liar.dusk.component

import kotlinx.css.*
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import kotlinx.html.BODY
import kotlinx.html.HEAD
import kotlinx.html.div
import kotlinx.html.style
import moe.liar.dusk.model.Resources
import moe.liar.dusk.utils.*
import moe.liar.utils.*

class Status private constructor(private val status: String, private val backgroundImg: Option<Resources>) : Component {
    data class Builder(private val status: String = "", private val background: Option<Resources> = none()) {
        fun setStatus(s: String) = copy(status = s)
        fun setBackGround(bg: Resources) = copy(background = bg.some())
        fun build() = Status(status, background)
    }

    override fun head(htmlHead: HEAD) = Unit
    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("background") {}
        div("status") { +status }

        style {
            css {
                rule(".background") {
                    width = 100.pct
                    height = 100.vh
                    backgroundImg.map {
                        background = "url(${it.uri()})"
                    }
                    backgroundPosition = "center"
                    backgroundRepeat = BackgroundRepeat.noRepeat
                    backgroundSize = "cover"
                    filter = "blur(10px)"
                }

                rule(".status") {
                    position = Position.absolute
                    top = 50.pct
                    left = 50.pct
                    transform {
                        translate((-50).pct, (-50).pct)
                    }
                    textAlign = TextAlign.center
                    fontSize = LinearDimension("3rem")
                    fontFamily =
                        "\"Source Sans Pro\", -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\"," +
                                " Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\";"

                }
            }
        }
    }

    override fun script(htmlBody: BODY) = Unit
}