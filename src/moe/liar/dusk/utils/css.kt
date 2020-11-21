package moe.liar.dusk.utils

import kotlinx.css.CSSBuilder
import kotlinx.html.STYLE
import kotlinx.html.unsafe

fun STYLE.css(builder: CSSBuilder.() -> Unit) {
    unsafe { +CSSBuilder().apply(builder).toString() }
}
