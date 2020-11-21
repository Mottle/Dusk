package moe.liar.dusk.component

import kotlinx.html.BODY
import kotlinx.html.HEAD

fun combine(vararg component: Component): Component = object : Component {
    override fun head(htmlHead: HEAD) = component.forEach { it.head(htmlHead) }

    override fun body(htmlBody: BODY) = component.forEach { it.body(htmlBody) }

    override fun script(htmlBody: BODY) = component.forEach { it.script(htmlBody) }
}