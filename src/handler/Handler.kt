package moe.liar.handler

import moe.liar.page.PBuilder

interface Handler {
    suspend fun handle(): PBuilder
}