package moe.liar.page

import kotlinx.css.BackgroundRepeat
import kotlinx.css.Color
import kotlinx.css.properties.*
import kotlinx.css.px
import kotlinx.html.*
import moe.liar.utils.Option
import moe.liar.utils.css
import moe.liar.utils.map

class Jumbotron(override val static: Option<String>) : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("jumbotron jumbotron-fluid vh-100") {
            id = "jumbotron"
            div("container") {
                div("display-2") {
                    +"Hello world"
                }
            }
        }
        style {
            css {
                rule("#jumbotron") {
                    animation(
                        name = "jumbotron-jump",
                        duration = 1.s,
//                        timing = Timing.ease,
//                        delay = 0.s,
//                        iterationCount = IterationCount("1"),
//                        direction = AnimationDirection.normal,
//                        fillMode = FillMode.none,
//                        playState = PlayState.running
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
            }
        }
    }

    override fun script(htmlBody: BODY) {}
}