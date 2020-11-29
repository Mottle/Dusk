package moe.liar.dusk.component

import kotlinx.css.Color
import kotlinx.css.LinearDimension
import kotlinx.css.px
import kotlinx.html.*
import moe.liar.dusk.model.Article
import moe.liar.dusk.model.CssRes
import moe.liar.dusk.model.JsRes
import moe.liar.dusk.utils.Option
import moe.liar.dusk.utils.css
import moe.liar.dusk.utils.none
import moe.liar.dusk.utils.some

class ArticleText private constructor(private val article: Article) : Component {
    class Builder(private val article: Option<Article> = none()) {
        fun setArticle(art: Article) = Builder(art.some())
        fun build() = ArticleText(article.forceGet() as Article)
    }

    override fun head(htmlHead: HEAD) = with(htmlHead) {
        styleLink(CssRes.path("prism.css").uri())
    }

    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("container article-container") {
            div("row") {
                div("col-lg") {}
                div("col-lg-9 article-text") {
                    unsafe {
                        +article.content
                    }
                }
                div("col-lg") {}
            }
        }

        style {
            css {
                rule(".article-text") {
                    minHeight = 1000.px
                    paddingTop = 100.px
                    paddingBottom = 100.px
                }

                rule("blockquote") {
                    margin = "1.6em 0"
                    padding = "0 0.8em"
                    borderLeft = "4px solid #ddd;"
                    color = Color("#777")
                }

                rule("body") {
                    fontSize = LinearDimension("1rem")
                }
            }
        }
    }

    override fun script(htmlBody: BODY) = with(htmlBody) {
        script(src = JsRes.path("prism.js").uri()) {}
        script(src = JsRes.path("codepanel.js").uri()) {}
    }
}