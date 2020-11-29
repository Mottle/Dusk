package moe.liar.dusk.component

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.scale
import kotlinx.css.properties.transform
import kotlinx.css.properties.transition
import kotlinx.html.*
import moe.liar.dusk.model.Article
import moe.liar.dusk.utils.css
import moe.liar.dusk.utils.getOrElse
import moe.liar.dusk.utils.map

class ArticleContent private constructor(private val data: List<Article>) : Component {
    class Builder(private val data: List<Article> = listOf()) {

        fun setArticlePreview(data: List<Article>) = Builder(data)
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

                rule(".img-limit") {
                    height = 300.px
                }

                rule(".card-body") {
                    display = Display.flex
                    flexDirection = FlexDirection.column
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

                media("(min-width: 1080px)") {
                    rule(".card:hover") {
                        transform {
                            scale(1.1, 1.1)
                        }
                    }
                }
            }
            unsafe {
                +"""@media screen and (min-width: 1080px) {
                    |   .card:hover { box-shadow: 0 5px 10px 5px rgba(110,110,110,.4) !important; }
                    |}""".trimMargin()
            }
        }

    }

    override fun script(htmlBody: BODY) = Unit
}

/**
 * 生成指定文章的card
 * 文章的url由id生成: /article/id
 */
private fun HtmlBlockTag.articleCard(article: Article) =
    with(this) {
        val link = "/article/${article.articleId}"
        div("card shadow-sm") {
            div("row no-gutters") {
                div("col-md-7 card-thumb") {
                    a(href = link) {
                        img(
                            classes = "card-img img-limit",
                            src = article.imageRes.map { it.uri() }.getOrElse("")
                        ) {
//                            attributes["loading"] = "lazy"
                            attributes["style"] = "object-fit: cover"
                        }
                    }
                }
                div("col-md-5") {
                    div("card-body card-arrangement") {
                        attributes["style"] = "height: 300px"
                        h5("card-title") { +article.title }
                        p("card-text") { small("text-muted") { +article.formatDate() } }
                        p("card-text") {
                            article.tags.forEach {
                                span("badge badge-light") { +it }
                            }
                        }
                        div {
                            attributes["style"] = "flex: 1;"
                        }
                        a(href = link) {
                            +"read more"
                        }
                    }
                }

            }
        }

    }