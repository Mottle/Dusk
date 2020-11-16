package moe.liar.handler

import io.ktor.locations.*
import moe.liar.model.ArticleDAO
import moe.liar.model.ImgRes
import moe.liar.model.LocalRandomBackground
import moe.liar.model.preview
import moe.liar.page.*
import moe.liar.utils.some


@KtorExperimentalLocationsAPI
@Location("/")
class IndexHandler : Handler<PBuilder> {
    override suspend fun handle(): PBuilder {
        val articles = ArticleDAO.getAll()
        val articlePreviews = articles.map { it.preview(50) }

        return CombinerBuilder().combine {
            NavBar.Builder().setLogo(ImgRes.path("logo.png")).build()
        }.combine {
            Jumbotron.Builder().setBackground(LocalRandomBackground.getRandom())
                .setMainTitle("Hello world").setSecondaryTitle("welcome to liar's blog").build()
        }.combine(::GoTop).combine {
            ArticleContent.Builder().setArticlePreview(articlePreviews).build()
        }.combine(::Footer).buildPage()
    }
}