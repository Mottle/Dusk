package moe.liar.page

import kotlinx.html.BODY
import kotlinx.html.HEAD
import moe.liar.utils.Option

class Combiner(override val static: Option<String>, private val pageBuilders: List<PBuilder> = listOf()) : Page {
    private val pages = pageBuilders.map { it(static) }
    override fun head(htmlHead: HEAD) {
        pages.forEach {
            it.head(htmlHead)
        }
    }

    override fun body(htmlBody: BODY) {
        pages.forEach {
            it.body(htmlBody)
        }
    }

    override fun script(htmlBody: BODY) {
        pages.forEach {
            it.script(htmlBody)
        }
    }
}

typealias CombinerBuilder = List<PBuilder>

fun CombinerBuilder(): CombinerBuilder = listOf()

fun CombinerBuilder.combine(pb: PBuilder): CombinerBuilder = this + pb

fun CombinerBuilder.buildPage(): PBuilder = { static: Option<String> ->
    Combiner(static, this)
}