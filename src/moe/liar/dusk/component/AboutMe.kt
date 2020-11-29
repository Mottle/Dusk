package moe.liar.dusk.component

import kotlinx.css.*
import kotlinx.css.Float
import kotlinx.html.*
import moe.liar.dusk.model.Resources
import moe.liar.dusk.utils.*

class AboutMe private constructor(private val avatar: Option<Resources>, private val name: Option<String>) : Component {
    data class Builder(private val avatar: Option<Resources> = none(), private val name: Option<String> = none()) {
        fun setAvatar(avatar: Resources) = copy(avatar = avatar.some())
        fun setName(name: String) = copy(name = name.some())
        fun build() = AboutMe(avatar, name)
    }

    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) = with(htmlBody) {
        div("row about-info-row") {
            div("col-lg") {}
            div("col-lg-6 about-info") {
                div("row") {
                    div("col-6") {
                        avatar.map {
                            img(src = it.uri(), classes = "avatar shadow")
                        }
                    }
                    div("col-6 name-row") {
                        name.map {
                            +it
                        }
                    }
                }
                div("info-center") {
                    a(href = "https://github.com/Mottle") {
                        +"github"
                    }
                }

                div("info-center") {
                    +"最菜Acmer"
                    br
                    +"擅长摸鱼"
                    br
                    +"FP厨"
                }
            }
            div("col-lg") {}
        }

        style {
            css {
                rule(".about-info-row") {
                    height = 55.vh
                    width = 100.pct
                }

                rule(".about-info") {
                    paddingTop = 50.px
                    paddingLeft = 20.px
                    paddingRight = 20.px
                    fontSize = 15.px
                }

                rule(".avatar") {
                    float = Float.right
                    width = 10.vh
                    height = 10.vh
                    borderRadius = 20.vh
                    backgroundColor = Color.white
                }

                rule(".name-row") {
                    display = Display.flex
                    alignItems = Align.center
                    fontSize = 5.vh
                }

                rule(".info-center") {
                    marginTop = 20.px
                    display = Display.flex
                    justifyContent = JustifyContent.center
                }
            }
        }
    }

    override fun script(htmlBody: BODY) = Unit
}