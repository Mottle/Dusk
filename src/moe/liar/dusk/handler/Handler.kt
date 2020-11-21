package moe.liar.dusk.handler

interface Handler<R> {
    suspend fun handle(): R
}