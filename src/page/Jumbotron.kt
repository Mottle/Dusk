package moe.liar.page

import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.animation
import kotlinx.css.properties.s
import kotlinx.html.*
import moe.liar.utils.Option
import moe.liar.utils.css
import moe.liar.utils.map

class Jumbotron(override val static: Option<String>) : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("jumbotron jumbotron-fluid vh-100") {
            id = "jumbotron"
            div("container focus-info") {
                h1("display-1 center-info row") {
                    +"Hello world"
                }
                p("display-4 center-info row") {
                    +"hello world to you"
                }
            }
        }
        style {
            css {
                rule("#jumbotron") {
                    animation(
                        name = "jumbotron-jump",
                        duration = 1.s,
                    )
                    border = "0"
                    marginBottom = 0.px
                    color = Color.white
                    static.map {
                        background = "url($it/img/background.jpg)"
                        backgroundPosition = "center"
                        backgroundRepeat = BackgroundRepeat.noRepeat
                        backgroundSize = "cover"
                    }
                }

                rule(".focus-info") {
                    position = Position.relative
                    opacity = 1
                    top = 30.pct
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

                rule(".focus-info .display-1") {
                    animation(
                        name = "jumbotron-jump",
                        duration = 1.s,
                        delay = 1.s,
                        timing = Timing.easeInOut
                    )
                }

                rule(".focus-info .display-4") {
                    animation(
                        name = "delay-hide",
                        duration = 1.s,
                        delay = 1.s
                    )
                    animation(
                        name = "jumbotron-jump",
                        duration = 1.s,
                        delay = 2.s,
                        timing = Timing.easeOut
                    )
                }

                rule(".focus-info .display-4") {
                    fontSize = LinearDimension("2rem")
                }

                rule(".center-info") {
                    textAlign = TextAlign.center
                    justifyContent = JustifyContent.center
                }
            }
        }
    }

    override fun script(htmlBody: BODY) {}
}