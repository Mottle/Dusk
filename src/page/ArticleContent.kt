package moe.liar.page

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*
import moe.liar.model.ArticleData
import moe.liar.utils.*

class ArticleContent(override val static: Option<String>, private val data: List<ArticleData>) : Page {
    override fun head(htmlHead: HEAD): Unit = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("container site-content") {
            div("row") {
                div("col-lg") {}
                div("col-lg-9") {
                    data.forEach {
                        articleCard(it)
                    }
                    articleCard(data.first())
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
                        scale(1.1, 1.1)
                    }
                }

                rule(".img-limit") {
                    height = 300.px
                }

                rule(".card-preview") {
                    height = 100.px
                }

                //中大型屏幕
                media("(min-width: 768px)") {
                    rule(".img-limit") {
                        borderRadius = LinearDimension("10px 0 0 10px")
                    }

                    rule(".card-arrangement") {
                        textAlign = TextAlign.right
                    }
                }

                media("(max-width: 767px)") {
                    rule(".img-limit") {
                        borderRadius = LinearDimension("10px 10px 0 0")
                    }
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
private fun HtmlBlockTag.articleCard(articleData: ArticleData) =
    with(this) {
        val link = "/article/${articleData.articleId}"
        div("card shadow-sm") {
            div("row no-gutters") {
                div("col-md-7 card-thumb") {
                    a(href = link) {
                        img(classes = "card-img img-limit", src = "/static/img/${articleData.imageName.getOrElse("404")}") {
                            attributes["style"] = "object-fit: cover"
                        }
                    }
                }
                div("col-md-5") {
                    div("card-body card-arrangement") {
                        attributes["style"] = "height: 300px"
                        h5("card-title") { +articleData.title }
                        p("card-text") { small("text-muted") { +articleData.date } }
                        p("card-text") {
                            articleData.tags.forEach {
                                span("badge badge-light") { +it }
                            }
                        }

                        p("card-text card-preview") { +articleData.preview }
                        a(link) {
                            +"read more"
                        }
                    }
                }
            }
        }

    }