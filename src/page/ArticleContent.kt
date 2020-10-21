package moe.liar.page

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*
import moe.liar.utils.*
import java.util.*

class ArticleContent(override val static: Option<String>) : Page {
    override fun head(htmlHead: HEAD): Unit = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("container site-content") {
            div("row") {
                div("col-lg") {}
                div("col-lg-9") {
                    articleCard(
                        1,
                        "title",
                        "1111/11/1",
                        "balabalbalbalbalbalbalbalbablababl",
                        listOf("abd", "aaa", "aads"),
                        "/static/logo.png".some()
                    )
                    articleCard(
                        1,
                        "title",
                        "1111/11/1",
                        "balabalbalbalbalbalbalbalbablababl",
                        listOf("abd", "aaa", "aads"),
                        "/static/logo.png".some()
                    )
                    articleCard(
                        1,
                        "title",
                        "1111/11/1",
                        "balabalbalbalbalbalbalbalbablababl", listOf("abd", "aaa", "aads"),

                        "/static/logo.png".some()
                    )
                }
                div("col-lg") {}
            }
        }

        style {
            css {
                rule(".site-content") {
                    marginTop = 50.px
                    marginBottom = 100.px
                }
                rule(".card") {
                    marginTop = 40.px
                    borderRadius = 10.px
                    transition(property = "all", duration = .6.s)
                }

                rule(".card:hover") {
                    transform {
                        scale(1.05, 1.05)
                    }
                }

                rule(".img-limit") {
                    height = 300.px
                }

                rule(".card-preview") {
                    height = 100.px
                }
            }
            unsafe {
                +".card:hover { box-shadow: 0 5px 10px 5px rgba(110,110,110,.4) !important; }"
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
private fun HtmlBlockTag.articleCard(
    id: Int,
    title: String,
    date: String,
    preview: String,
    tags: List<String>,
    imageUri: Option<String>
) =
    with(this) {
        val link = "/article/$id"
        div("card shadow-sm") {
            div("row no-gutters") {
                div("col-md-7 card-thumb") {
                    a(href = link) {
                        img(classes = "card-img img-limit", src = imageUri.getOrElse("")) {
                            attributes["style"] = "object-fit: cover"
                        }
                    }
                }
                div("col-md-5") {
                    div("card-body") {
                        attributes["style"] = "height: 300px"
                        h5("card-title") { +title }
                        p("card-text") { small("text-muted") { +date } }
                        p("card-text") {
                            tags.forEach {
                                span("badge badge-light") { +it }
                            }
                        }

                        p("card-text card-preview") { +preview }
                        a("link") {
                            attributes["style"] = "float: right"
                            +"read more"
                        }
                    }
                }
            }
        }

    }