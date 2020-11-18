package moe.liar.page

import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.animation
import kotlinx.css.properties.s
import kotlinx.html.*
import moe.liar.model.JsRes
import moe.liar.model.Resources
import moe.liar.utils.Option
import moe.liar.utils.css
import moe.liar.utils.map
import moe.liar.utils.none

class Jumbotron private constructor(
    private val backgroundImg: Option<Resources>,
    private val heightPct: Int,
    private val mainTitle: String,
    private val secondaryTitle: String,
    private val sizeOfFont: Int
) : Page {
    data class Builder(
        private val backgroundImg: Option<Resources> = none(), private val heightPct: Int = 100,
        private val mainTitle: String = "", private val secondaryTitle: String = "",
        private val fontSize: Int = 6
    ) {
        fun setBackground(bg: Option<Resources>) = copy(backgroundImg = bg)
        fun setHeight(h: Int) = copy(heightPct = h)
        fun setMainTitle(title: String) = copy(mainTitle = title)
        fun setSecondaryTitle(title: String) = copy(secondaryTitle = title)
        fun setFontSize(size: Int) = copy(fontSize = size)
        fun build() = Jumbotron(backgroundImg, heightPct, mainTitle, secondaryTitle, fontSize)
    }

    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("jumbotron jumbotron-fluid jumbotron-hide") {
            id = "jumbotron"
            div("container focus-info") {
                h1("display-1 center-info jumbotron-hide row") {
                    id = "main-title"
                    +mainTitle
                }
                p("display-4 center-info jumbotron-hide row") {
                    id = "secondary-title"
                    +secondaryTitle
                }
            }
        }
        style {
            css {
                rule("#jumbotron") {
                    height = heightPct.vh
                    border = "0"
                    marginBottom = 0.px
                    color = Color.white
                    backgroundImg.map {
                        background = "url(${it.uri()})"
                    }
                    backgroundPosition = "center"
                    backgroundRepeat = BackgroundRepeat.noRepeat
                    backgroundSize = "cover"
                }

                rule(".focus-info") {
                    position = Position.relative
                    top = 40.pct
                }

                rule(".jumbotron-hide") { opacity = 0 }
                rule(".jumbotron-show") { opacity = 1 }

                rule(".jmp") {
                    animation(
                        name = "jumbotron-jump",
                        duration = 1.s,
                        timing = Timing.easeInOut
                    )
                }

                rule(".jmp1") {
                    animation(
                        name = "delay-hide",
                        duration = 1.s
                    )
                    animation(
                        name = "jumbotron-jump",
                        duration = 1.s,
                        delay = 1.s,
                        timing = Timing.easeInOut
                    )
                }

                rule(".jmp2") {
//                    opacity = 1
                    animation(
                        name = "delay-hide",
                        duration = 2.s,
                    )
                    animation(
                        name = "jumbotron-jump",
                        duration = 1.s,
                        delay = 2.s,
                        timing = Timing.easeOut
                    )
                }

                rule(".center-info") {
                    textAlign = TextAlign.center
                    justifyContent = JustifyContent.center
                }

//                中大型屏幕
                media("(min-width: 768px)") {
                    rule("#main-title") {
                        fontSize = LinearDimension("${sizeOfFont}rem !important")
                    }

                    rule("#secondary-title") {
                        fontSize = LinearDimension("${sizeOfFont / 2}rem !important")
                    }
                }

                media("(max-width: 767px)") {
                    rule("#main-title") {
                        fontSize = LinearDimension("${sizeOfFont / 2}rem !important")
                    }

                    rule("#secondary-title") {
                        fontSize = LinearDimension("${sizeOfFont / 4}rem !important")
                    }
                }
            }
        }
    }

    override fun script(htmlBody: BODY) = with(htmlBody) {
        script(src = JsRes.path("jumbotron.js").uri()) {}
    }
}