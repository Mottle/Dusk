package moe.liar.page

import kotlinx.css.*
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
                    top = 30.pct
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