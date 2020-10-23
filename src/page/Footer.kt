package moe.liar.page

import kotlinx.css.*
import kotlinx.html.*
import moe.liar.utils.css

class Footer : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {

        div("site-footer") {
            div("container") {
                div("row") {
                    div("col-lg order-3") {
                        unsafe {
                            +"<a rel=\"license\" href=\"http://creativecommons.org/licenses/by-nc-sa/4.0/\"><img alt=\"知识共享许可协议\" style=\"border-width:0\" src=\"https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png\" /></a><br />本作品采用<a rel=\"license\" href=\"http://creativecommons.org/licenses/by-nc-sa/4.0/\">知识共享署名-非商业性使用-相同方式共享 4.0 国际许可协议</a>进行许可。"
                        }
                    }
                    div("col-lg order-2") {
                        attributes["style"] = "margin: 0"
                    }
                    div("col-lg order-1") {
                        span("copyright") { +"Copyright Reserved © 2020, Powered by Liar.\n" }
                    }
                }
            }
        }
        style {
            css {
                rule(".site-footer") {
                    width = 100.pct
//                    height = 400.px
                    backgroundColor = Color("#323437")
                    color = Color.white
                }

                rule(".site-footer .container .row .col-lg") {
                    marginTop = 50.px
                    marginBottom = 50.px
                }

                rule(".copyright") {
                    position = Position.absolute
                    bottom = 0.px
                }
            }
        }
    }

    override fun script(htmlBody: BODY) = Unit
}