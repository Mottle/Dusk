package moe.liar.dusk.component

import kotlinx.html.BODY
import kotlinx.html.HEAD

interface Component {
    fun head(htmlHead: HEAD)
    fun body(htmlBody: BODY)
    fun script(htmlBody: BODY)
}