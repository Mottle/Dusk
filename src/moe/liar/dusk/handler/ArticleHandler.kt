package moe.liar.dusk.handler

import io.ktor.features.*
import io.ktor.locations.*
import moe.liar.dusk.component.*
import moe.liar.dusk.model.Article
import moe.liar.dusk.model.ArticleDAO
import moe.liar.dusk.model.ImgRes
import moe.liar.dusk.model.LocalRandomBackground
import moe.liar.dusk.utils.getOrElse
import moe.liar.dusk.utils.map

@KtorExperimentalLocationsAPI
@Location("/article/{id}")
data class ArticleHandler(private val id: Int) : Handler<Component> {
    override suspend fun handle(): Component {
        val art = ArticleDAO.get(id)
        if (art.isNone()) throw NotFoundException()
        val navBar = NavBar.Builder().setLogo(ImgRes.path("logo.png")).setForceShown().build()
        val jumbotron = Jumbotron.Builder().setBackground(LocalRandomBackground.getRandom())
            .setMainTitle(art.map { it.title }.getOrElse(""))
            .setSecondaryTitle(art.map { it.formatDate() }.getOrElse(""))
            .setFontSize(4)
            .setHeight(60).build()
        val goTop = GoTop()
        val articleText = ArticleText.Builder().setArticle(art.forceGet() as Article).build()
        val footer = Footer()

        return combine(navBar, jumbotron, goTop, articleText, footer)
    }
}