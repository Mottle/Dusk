package moe.liar.page

import kotlinx.css.BackgroundRepeat
import kotlinx.css.LinearDimension
import kotlinx.css.pct
import kotlinx.css.px
import kotlinx.html.*
import moe.liar.utils.*
import java.util.*

class ArticleContent(override val static: Option<String>) : Page {
    override fun head(htmlHead: HEAD): Unit = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {

        div("container") {
            div("row" ) {
                div("col-md") {}
                div("col-md-8") {
                    articleCard(1, "title", "1111/11/1", "balabalbalbalbalbalbalbalbablababl", "/static/logo.png".some())
                    articleCard(1, "title", "1111/11/1", "balabalbalbalbalbalbalbalbablababl", "/static/logo.png".some())
                    articleCard(1, "title", "1111/11/1", "balabalbalbalbalbalbalbalbablababl", "/static/logo.png".some())
                }
                div("col-md") {}
            }
        }
    }

    override fun script(htmlBody: BODY) {
    }
}

/**
 * @param title 文章名
 * @param date 文章发表日期
 * @param preview 文章预览
 * 生成指定文章的card
 * 文章的url由id生成: /article/id
 */
private fun HtmlBlockTag.articleCard(id: Int, title: String, date: String, preview: String, imageUri: Option<String>) = with(this) {
    div("card shadow") {
        div("row no-gutters") {
            div("row-6 col-md-7 card-thumb") {
                img(classes = "card-img img-limit", src = imageUri.getOrElse("")) {
                    attributes["style"] = "object-fit: cover"
                }
            }
            div("row-6 col-md-5") {
                div("card-body") {
                    h5("card-title") { +title }
                    p("card-text") { small("text-muted") { +date } }
                    p("card-text") { +preview }
                    a("/article/$id") { +"read more" }
                }
            }
        }
    }
    style {
        css {
            rule(".card") {
                marginTop = 30.px
                borderRadius = 10.px
            }
            rule(".img-limit") {
                height = 300.px
            }
        }
    }
}