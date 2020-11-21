package moe.liar.dusk.component

import kotlinx.css.Position
import kotlinx.css.px
import kotlinx.html.*
import moe.liar.dusk.model.IconSvgRes
import moe.liar.dusk.model.JsRes
import moe.liar.dusk.utils.css

class GoTop : Component {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        button(type = ButtonType.button, classes = "btn btn-dark d-none d-lg-block") {
            id = "go-top-btn"
            img(src = IconSvgRes.path("arrow-up.svg").uri()) {
                width = "32px"
                height = "32px"
                role = "img"
                attributes["focusable"] = "false"
            }
        }
        style {
            css {
                rule("#go-top-btn") {
                    position = Position.fixed
                    zIndex = 1000
                    bottom = 10.px
                    right = 10.px
                    paddingLeft = 6.px
                    paddingRight = 6.px
                    opacity = 0
                }
            }
        }
    }

    override fun script(htmlBody: BODY): Unit = with(htmlBody) {
        script(src = JsRes.path("gotop.js").uri()) {}
    }
}