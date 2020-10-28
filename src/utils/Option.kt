package moe.liar.utils

interface Option<out T> {
    fun forceGet(): T?
    fun isNone(): Boolean
}

object None : Option<Nothing> {
    override fun forceGet(): Nothing? = null
    override fun isNone(): Boolean = true
}

data class Some<out T>(val value: T) : Option<T> {
    override fun forceGet(): T? = value
    override fun isNone(): Boolean = false
}

fun <T> Option<T>.getOrElse(e: T): T = when (this) {
    is Some -> value
    else -> e
}

fun <T> T.option(): Option<T> = when (this) {
    null -> None
    else -> this.some()
}

fun <A> A.some(): Option<A> = Some(this)
fun <A> none(): Option<A> = None
fun <T, R> Option<T>.map(fn: (T) -> R): Option<R> = when (this) {
    is Some -> value.let(fn).some()
    else -> none()
}

fun <T, R> Option<T>.flatMap(fn: (T) -> Option<R>): Option<R> = when (this) {
    is Some -> value.let(fn)
    else -> none()
}