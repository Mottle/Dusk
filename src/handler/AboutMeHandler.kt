package moe.liar.handler

import io.ktor.locations.*
import moe.liar.model.ImgRes
import moe.liar.model.LocalRandomBackground
import moe.liar.page.*
import moe.liar.utils.some

@KtorExperimentalLocationsAPI
@Location("/about")
class AboutMeHandler : Handler<PBuilder> {
    override suspend fun handle(): PBuilder {
        return CombinerBuilder().combine {
            NavBar.Builder().setLogo(ImgRes.path("logo.png")).setForceShown()
                .build()
        }.combine {
            Jumbotron.Builder().setBackground(LocalRandomBackground.getRandom()).setHeight(45)
                .setMainTitle("about me")
                .build()
        }.combine {
            AboutMe.Builder().setName("Liar").setAvatar(ImgRes.path("avatar.png"))
                .build()
        }.combine(::Footer).buildPage()
    }
}