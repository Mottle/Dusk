package moe.liar.handler

import moe.liar.page.PBuilder

interface Handler<R> {
    suspend fun handle(): R
}