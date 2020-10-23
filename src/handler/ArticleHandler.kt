package moe.liar.handler

import io.ktor.locations.*
import moe.liar.page.PBuilder

@KtorExperimentalLocationsAPI
@Location("/article/{id}")
data class ArticleHandler(val id: Int) : Handler {
    override suspend fun handle(): PBuilder {
        TODO("Not yet implemented")
    }
}