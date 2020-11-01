package moe.liar

import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import moe.liar.model.ArticleDAO
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @KtorExperimentalLocationsAPI
    @Test
    fun testRoot() {
        withTestApplication({  module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }
}
