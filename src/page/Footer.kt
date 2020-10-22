package moe.liar.page

import kotlinx.css.Color
import kotlinx.css.pct
import kotlinx.css.px
import kotlinx.html.BODY
import kotlinx.html.HEAD
import kotlinx.html.div
import kotlinx.html.style
import moe.liar.utils.css

class Footer : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {

        div(" site-footer") {
            div("container") {

            }
        }
        style {
            css {
                rule(".site-footer") {
                    width = 100.pct
                    height = 400.px
                    backgroundColor = Color("#323437")
                }
            }
        }
    }

    override fun script(htmlBody: BODY) = Unit
}