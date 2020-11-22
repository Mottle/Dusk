package moe.liar.dusk.handler

import io.ktor.locations.*
import moe.liar.dusk.component.*
import moe.liar.dusk.model.ArticleDAO
import moe.liar.dusk.model.ImgRes
import moe.liar.dusk.model.LocalRandomBackground
import moe.liar.dusk.model.preview


@KtorExperimentalLocationsAPI
@Location("/")
class IndexHandler : Handler<Component> {
    override suspend fun handle(): Component {
        val articles = ArticleDAO.getAll()
//        val articlePreviews = articles.map { it.preview(0) }

        val navBar = NavBar.Builder().setLogo(ImgRes.path("logo.png")).build()
        val jumbotron = Jumbotron.Builder().setBackground(LocalRandomBackground.getRandom())
            .setMainTitle("Hello world").setSecondaryTitle("welcome to liar's blog").build()
        val goTop = GoTop()
        val articleContent = ArticleContent.Builder().setArticlePreview(articles).build()
        val footer = Footer()
        return combine(navBar, jumbotron, goTop, articleContent, footer)
    }
}