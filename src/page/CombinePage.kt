package moe.liar.page

import kotlinx.html.BODY
import kotlinx.html.HEAD
import moe.liar.utils.Option

class CombinePage(override val static: Option<String>) : Page {
    private var pages = mutableListOf<Page>()

    fun combine(fn: (Option<String>) -> Page) {
        val page = fn(static)
        pages.add(page)
    }

    override fun head(htmlHead: HEAD) = pages.forEach {
        it.head(htmlHead)
    }

    override fun body(htmlBody: BODY) = pages.forEach {
        it.body(htmlBody)
    }

    override fun script(htmlBody: BODY) = pages.forEach {
        it.script(htmlBody)
    }
}