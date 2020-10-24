package moe.liar.utils

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.html.HtmlRenderer

data class Markdown(private val markdownText: String) {
    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build()
    val html = lazy {
        renderer.render(
            parser.parse(markdownText))
    }

    fun toHtml(): String = html.value
}