package moe.liar.page

import kotlinx.html.BODY
import kotlinx.html.HEAD
import moe.liar.utils.Option

class Combiner(private val pageBuilders: List<PBuilder> = listOf()) : Page {
    private val pages = pageBuilders.map { it() }
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

fun CombinerBuilder.buildPage(): PBuilder = {
    Combiner(this)
}