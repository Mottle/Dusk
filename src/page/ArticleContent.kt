package moe.liar.page

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*
import moe.liar.model.ArticleInfo
import moe.liar.utils.*
import java.util.*
import kotlin.random.Random

class ArticleContent(private val data: List<ArticleInfo>) : Page {
    override fun head(htmlHead: HEAD): Unit = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("container site-content") {
            div("row") {
                div("col-lg") {}
                div("col-lg-9") {
                    data.forEach {
                        articleCard(it)
                    }
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

    override fun script(htmlBody: BODY) = Unit
}

/**
 * 生成指定文章的card
 * 文章的url由id生成: /article/id
 */
private fun HtmlBlockTag.articleCard(articleInfo: ArticleInfo) =
    with(this) {
        val link = "/article/${articleInfo.articleId}"
        div("card shadow-sm") {
            div("row no-gutters") {
                div("col-md-7 card-thumb") {
                    a(href = link) {
                        img(classes = "card-img img-limit", src = articleInfo.imageRes.map { it.uri() }.getOrElse("")) {
                            attributes["loading"] = "lazy"
                            attributes["style"] = "object-fit: cover"
                        }
                    }
                }
                div("col-md-5") {
                    div("card-body card-arrangement") {
                        attributes["style"] = "height: 300px"
                        h5("card-title") { +articleInfo.title }
                        p("card-text") { small("text-muted") { +articleInfo.date } }
                        p("card-text") {
                            articleInfo.tags.forEach {
                                span("badge badge-light") { +it }
                            }
                        }

                        p("card-text card-preview") { +articleInfo.preview }
                        a(link) {
                            +"read more"
                        }
                    }
                }
            }
        }

    }