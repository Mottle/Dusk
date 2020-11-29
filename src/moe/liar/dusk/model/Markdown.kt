package moe.liar.dusk.model

import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension
import com.vladsch.flexmark.html.AttributeProvider
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory
import com.vladsch.flexmark.html.renderer.AttributablePart
import com.vladsch.flexmark.html.renderer.LinkResolverContext
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.data.MutableDataHolder
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.html.MutableAttributes

typealias RowMarkdown = String

data class Markdown(private val markdownText: RowMarkdown) {
    private val option = MutableDataSet().set(
        Parser.EXTENSIONS, listOf(
            StrikethroughSubscriptExtension.create(),
            ArticleExtension.create()
        )
    )
    private val parser = Parser.builder(option).build()
    private val renderer = HtmlRenderer.builder(option).build()
    private val document = lazy { parser.parse(markdownText) }
    private val html = lazy {
        renderer.render(document.value)
    }

    fun toHtml(): String = html.value.decodeMarkdownLatexBraces()
}

object MarkdownFactory {
    fun get(rowMarkdown: RowMarkdown) = Markdown(rowMarkdown.encodeMarkdownLatexBraces())
}

private fun RowMarkdown.encodeMarkdownLatexBraces() = this.encodeLeftBraces().encodeRightBraces()
private fun RowMarkdown.encodeLeftBraces() = this.replace("\\{", LeftBracesFlag)
private fun RowMarkdown.encodeRightBraces() = this.replace("\\}", RightBracesFlag)
private const val LeftBracesFlag = "#LefT#"
private const val RightBracesFlag = "#RiGhT#"

private fun RowMarkdown.decodeMarkdownLatexBraces() = this.decodeLeftBraces().decodeRightBraces()
private fun RowMarkdown.decodeLeftBraces() = this.replace(LeftBracesFlag, "\\{")
private fun RowMarkdown.decodeRightBraces() = this.replace(RightBracesFlag, "\\}")

private class ArticleExtension : HtmlRenderer.HtmlRendererExtension {
    companion object {
        fun create() = ArticleExtension()
    }

    override fun rendererOptions(options: MutableDataHolder) {}

    override fun extend(htmlRendererBuilder: HtmlRenderer.Builder, rendererType: String) {
        htmlRendererBuilder.attributeProviderFactory(ArticleAttributeProvider.factory())
    }
}

private class ArticleAttributeProvider : AttributeProvider {
    companion object {
        fun factory() = object : IndependentAttributeProviderFactory() {
            override fun apply(context: LinkResolverContext): AttributeProvider = ArticleAttributeProvider()
        }
    }

    override fun setAttributes(node: Node, part: AttributablePart, attributes: MutableAttributes) {
        if (node is FencedCodeBlock) {
            attributes.addValue("class", "line-numbers")
            attributes.addValue("class", "match-braces")
            attributes.addValue("class", "rainbow-braces")
        }
    }
}