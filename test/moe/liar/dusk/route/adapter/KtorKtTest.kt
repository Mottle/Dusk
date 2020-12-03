package moe.liar.dusk.route.adapter

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import kotlinx.html.body
import kotlinx.html.div
import moe.liar.dusk.route.respondHtml
import moe.liar.dusk.utils.html
import moe.liar.dusk.utils.multiMapOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class KtorKtTest {
    @Test
    fun headerToMultiMapTest() {
        val header = Headers.build {
            append("xxx", "1")
            append("xxxx", "1")
            append("xxx", "2")
            append("xxx", "1")
        }
        val map = multiMapOf(Pair("xxx", "1"), Pair("xxxx", "1"), Pair("xxx", "2"), Pair("xxx", "1"))
        assertEquals(header.toMultiMap(), map)
    }

    private val testHtml = html {
        body {
            div {
                +"xxxx"
            }
        }
    }

    @KtorExperimentalLocationsAPI
    suspend fun Application.app() {
        val router = KtorRouter(this)
        router.route(moe.liar.dusk.route.HttpMethod.GET, "/") {
            response.respondHtml {
                body {
                    div {
                        +"xxxx"
                    }
                }
            }
        }
    }

    @KtorExperimentalLocationsAPI
    @Test
    fun testRouter() {
        withTestApplication({ runBlocking { app() } }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(testHtml, response.content)
            }
        }
    }
}