package moe.liar.page

import kotlinx.html.BODY
import kotlinx.html.HEAD

interface Page {
    fun head(htmlHead: HEAD)
    fun body(htmlBody: BODY)
    fun script(htmlBody: BODY)
}