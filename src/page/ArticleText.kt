package moe.liar.page

import kotlinx.css.px
import kotlinx.html.*
import moe.liar.model.Article
import moe.liar.utils.Option
import moe.liar.utils.css
import moe.liar.utils.none
import moe.liar.utils.some

class ArticleText private constructor(private val article: Article): Page {
    class Builder(private val article: Option<Article> = none()) {
        fun setArticle(art: Article) = Builder(art.some())
        fun build() = ArticleText(article.forceGet() as Article)
    }

    override fun head(htmlHead: HEAD) = Unit

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
            }
        }
    }

    override fun script(htmlBody: BODY) = Unit
}