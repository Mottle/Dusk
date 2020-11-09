package moe.liar.handler

import io.ktor.features.*
import io.ktor.locations.*
import moe.liar.model.Article
import moe.liar.model.ArticleDAO
import moe.liar.model.ImgRes
import moe.liar.model.RandomBackground
import moe.liar.page.*
import moe.liar.utils.getOrElse
import moe.liar.utils.map
import moe.liar.utils.some

@KtorExperimentalLocationsAPI
@Location("/article/{id}")
data class ArticleHandler(private val id: Int) : Handler<PBuilder> {
    override suspend fun handle(): PBuilder {
        val art = ArticleDAO.get(id)
        if (art.isNone()) throw NotFoundException()
        return CombinerBuilder().combine {
            NavBar.Builder().setLogo(ImgRes.path("logo.png").some()).setForceShown().build()
        }.combine {
            Jumbotron.Builder().setBackground(RandomBackground.getRandom())
                .setMainTitle(art.map { it.title }.getOrElse(""))
                .setSecondaryTitle(art.map { it.formatDate() }.getOrElse(""))
                .setFontSize(4)
                .setHeight(60).build()
        }.combine(::GoTop).combine {
            ArticleText.Builder().setArticle(art.forceGet() as Article).build()
        }.combine(::Footer).buildPage()
    }
}