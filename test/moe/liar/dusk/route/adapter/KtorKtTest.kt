package moe.liar.dusk.route.adapter

import io.ktor.http.*
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
}