package moe.liar.handler

import io.ktor.locations.*
import moe.liar.model.ArticleInfo
import moe.liar.model.ImgRes
import moe.liar.model.Link
import moe.liar.page.*
import moe.liar.utils.some
import kotlin.random.Random


@KtorExperimentalLocationsAPI
@Location("/")
class IndexHandler : Handler {
    override suspend fun handle(): PBuilder = CombinerBuilder().combine {
        NavBar(
            logo = ImgRes.path("logo.png").some()
        )
    }.combine {
        Jumbotron(ImgRes.path("background.jpg").some())
    }.combine(::GoTop).combine {
        ArticleContent(
            listOf(
                ArticleInfo(
                    1,
                    "1",
                    "1111/11/1",
                    "xxxxxx",
                    listOf("a", "b"),
                    Link("https://random.52ecy.cn/randbg.php/${Random.nextInt()}?size=1").some()
                ),
                ArticleInfo(
                    2,
                    "2",
                    "1111/11/1",
                    "xxxxxx",
                    listOf("a", "b"),
                    Link("https://random.52ecy.cn/randbg.php/${Random.nextInt()}?size=1").some()
                ),
                ArticleInfo(
                    3,
                    "3",
                    "1111/11/1",
                    "xxxxxx",
                    listOf("a", "b"),
                    Link("https://random.52ecy.cn/randbg.php/${Random.nextInt()}?size=1").some()
                )
            )
        )
    }.combine(::Footer).buildPage()
}