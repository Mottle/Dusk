package moe.liar.utils

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.html.HtmlRenderer

data class Markdown(private val markdownText: String) {
    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build()
    private val document = lazy { parser.parse(markdownText) }
    private val html = lazy {
        renderer.render(document.value)
    }
    fun toDocument() = document.value
    fun toHtml(): String = html.value
}