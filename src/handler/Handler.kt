package moe.liar.handler

interface Handler<R> {
    suspend fun handle(): R
}