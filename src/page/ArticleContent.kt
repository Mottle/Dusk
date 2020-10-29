package moe.liar.page

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*
import moe.liar.model.ArticlePreview
import moe.liar.utils.*
import java.text.SimpleDateFormat

class ArticleContent private constructor(private val data: List<ArticlePreview>) : Page {
    class Builder(private val data: List<ArticlePreview> = listOf()) {

        fun setArticlePreview(data: List<ArticlePreview>) = Builder(data)
        fun build(): ArticleContent = ArticleContent(data)
    }

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
private fun HtmlBlockTag.articleCard(articlePreview: ArticlePreview) =
    with(this) {
        val link = "/article/${articlePreview.articleId}"
        div("card shadow-sm") {
            div("row no-gutters") {
                div("col-md-7 card-thumb") {
                    a(href = link) {
                        img(classes = "card-img img-limit", src = articlePreview.imageRes.map { it.uri() }.getOrElse("")) {
                            attributes["loading"] = "lazy"
                            attributes["style"] = "object-fit: cover"
                        }
                    }
                }
                div("col-md-5") {
                    div("card-body card-arrangement") {
                        attributes["style"] = "height: 300px"
                        h5("card-title") { +articlePreview.title }
                        p("card-text") { small("text-muted") { +articlePreview.formatDate()  } }
                        p("card-text") {
                            articlePreview.tags.forEach {
                                span("badge badge-light") { +it }
                            }
                        }
                        div("card-text card-preview") {
                            unsafe { +articlePreview.preview }
                        }
                        a(link) {
                            +"read more"
                        }
                    }
                }
            }
        }

    }