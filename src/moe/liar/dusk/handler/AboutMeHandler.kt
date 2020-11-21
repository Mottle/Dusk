package moe.liar.dusk.handler

import io.ktor.locations.*
import moe.liar.dusk.component.*
import moe.liar.dusk.model.ImgRes
import moe.liar.dusk.model.LocalRandomBackground

@KtorExperimentalLocationsAPI
@Location("/about")
class AboutMeHandler : Handler<Component> {
    override suspend fun handle(): Component {
        val navBar = NavBar.Builder().setLogo(ImgRes.path("logo.png")).setForceShown()
            .build()
        val jumbotron = Jumbotron.Builder().setBackground(LocalRandomBackground.getRandom()).setHeight(45)
            .setMainTitle("about me")
            .build()
        val aboutMe = AboutMe.Builder().setName("Liar").setAvatar(ImgRes.path("avatar.png"))
            .build()
        val footer = Footer()

        return combine(navBar, jumbotron, aboutMe, footer)
    }
}