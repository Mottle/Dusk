package moe.liar.page

import kotlinx.html.BODY
import kotlinx.html.HEAD
import moe.liar.utils.Option

interface Page {
    val static: Option<String>
    fun head(htmlHead: HEAD)
    fun body(htmlBody: BODY)
    fun script(htmlBody: BODY)
}