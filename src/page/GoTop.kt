package moe.liar.page

import kotlinx.css.Position
import kotlinx.css.px
import kotlinx.html.*
import moe.liar.utils.Option
import moe.liar.utils.css
import moe.liar.utils.map

class GoTop(override val static: Option<String>) : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        button(type = ButtonType.button, classes = "btn btn-light d-none d-lg-block") {
            id = "go-top-btn"
            static.map {
                img(src = "$it/img/icon/svg/arrow-up.svg") {
                    width = "32px"
                    height = "32px"
                    role = "img"
                    attributes["focusable"] = "false"
                }
            }
        }
        style {
            css {
                rule("#go-top-btn") {
                    position = Position.fixed
                    zIndex = 1000
                    bottom = 100.px
                    right = 100.px
                    paddingLeft = 6.px
                    paddingRight = 6.px
                    opacity = 0.5
                }
            }
        }
    }

    override fun script(htmlBody: BODY): Unit = with(htmlBody) {
        static.map {
            script(src = "$it/js/gotop.js") {}
        }
    }
}