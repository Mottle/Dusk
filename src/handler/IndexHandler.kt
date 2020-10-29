package moe.liar.handler

import io.ktor.locations.*
import moe.liar.model.*
import moe.liar.page.*
import moe.liar.utils.some
import kotlin.random.Random


@KtorExperimentalLocationsAPI
@Location("/")
class IndexHandler : Handler<PBuilder> {
    override suspend fun handle(): PBuilder {
        val articles = ArticleDAO.getAll()
        val articlePreviews = articles.map { it.preview(50) }

        return CombinerBuilder().combine {
            NavBar.Builder().setLogo(ImgRes.path("logo.png").some()).build()
        }.combine {
            Jumbotron.Builder().setBackground(ImgRes.path("background.jpg").some())
                .setMainTitle("Hello world").setSecondaryTitle("welcome to DUSK").build()
        }.combine(::GoTop).combine {
            ArticleContent.Builder().setArticlePreview(articlePreviews).build()
        }.combine(::Footer).buildPage()
    }
}