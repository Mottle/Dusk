package moe.liar.handler

import io.ktor.features.*
import io.ktor.locations.*
import moe.liar.model.Article
import moe.liar.model.ArticleDAO
import moe.liar.model.ImgRes
import moe.liar.page.*
import moe.liar.utils.some

@KtorExperimentalLocationsAPI
@Location("/article/{id}")
data class ArticleHandler(val id: String) : Handler<PBuilder> {
    override suspend fun handle(): PBuilder {
        val _id = id.toIntOrNull() ?: throw NotFoundException()
        val art = ArticleDAO.get(_id)
        if(art.isNone()) throw NotFoundException()
        return CombinerBuilder().combine {
            NavBar.Builder().setLogo(ImgRes.path("logo.png").some()).setForceShown().build()
        }.combine {
            Jumbotron.Builder().setBackground(ImgRes.path("background.jpg").some()).setHeight(50).build()
        }.combine(::GoTop).combine {
            ArticleText.Builder().setArticle(art.forceGet() as Article).build()
        }.combine(::Footer).buildPage()
    }
}