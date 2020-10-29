package moe.liar.utils

import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.util.data.MutableDataSet

typealias RowMarkdown = String

data class Markdown(private val markdownText: RowMarkdown) {
    private val option = MutableDataSet().set(Parser.EXTENSIONS, listOf(StrikethroughSubscriptExtension.create()))
    private val parser = Parser.builder(option).build()
    private val renderer = HtmlRenderer.builder(option).build()
    private val document = lazy { parser.parse(markdownText) }
    private val html = lazy {
        renderer.render(document.value)
    }
    fun toDocument() = document.value
    fun toHtml(): String = html.value.decodeMarkdownLatexBraces()
}

object MarkdownFactory {
    fun get(rowMarkdown: RowMarkdown) = Markdown(rowMarkdown.encodeMarkdownLatexBraces())
}

private fun RowMarkdown.encodeMarkdownLatexBraces() = this.encodeLeftBraces().encodeRightBraces()
private fun RowMarkdown.encodeLeftBraces() = this.replace("\\{", LeftBracesFlag)
private fun RowMarkdown.encodeRightBraces() = this.replace("\\}", RightBracesFlag)
private const val LeftBracesFlag = "#LEFT#"
private const val RightBracesFlag = "#RIGHT#"

private fun RowMarkdown.decodeMarkdownLatexBraces() = this.decodeLeftBraces().decodeRightBraces()
private fun RowMarkdown.decodeLeftBraces() = this.replace(LeftBracesFlag, "\\{")
private fun RowMarkdown.decodeRightBraces() = this.replace(RightBracesFlag, "\\}")