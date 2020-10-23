package moe.liar.page

import kotlinx.html.BODY
import kotlinx.html.HEAD
import moe.liar.page.Page

class ArticleText : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {

    }

    override fun script(htmlBody: BODY) = Unit
}