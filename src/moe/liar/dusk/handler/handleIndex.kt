package moe.liar.dusk.handler

import moe.liar.dusk.component.*
import moe.liar.dusk.model.ArticleDAO
import moe.liar.dusk.model.ImgRes
import moe.liar.dusk.model.LocalRandomBackground

fun handleIndex(): Component {
    val articles = ArticleDAO.getAll()
    val navBar = NavBar.Builder().setLogo(ImgRes.path("logo.png")).build()
    val jumbotron = Jumbotron.Builder().setBackground(LocalRandomBackground.getRandom())
        .setMainTitle("Hello world").setSecondaryTitle("welcome to liar's blog").build()
    val goTop = GoTop()
    val articleContent = ArticleContent.Builder().setArticlePreview(articles).build()
    val footer = Footer()
    return combine(navBar, jumbotron, goTop, articleContent, footer)
}