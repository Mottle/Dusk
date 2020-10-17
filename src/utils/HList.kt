package moe.liar.utils

interface HList<out T> {
    val length: Int
}

data class HNode<out T, out H : HList<*>>(
    val value: T,
    val next: H,
    override val length: Int
) : HList<H>

object HNil : HList<Nothing> {
    override val length: Int
        get() = 0
}

//infix fun <A, B> A.hcons(b: B): HNode<B, HNode<A, HNil>> = HNode(value = b, length = 3, next = HNode(this, HNil, 1))
//infix fun <A, H : HList<*>> H.hcons(a: A): HNode<A, H> = HNode(a, this, length + 1)
infix fun <A> A.hcons(h: HList<*>): HNode<A, HList<*>> = HNode(this, h, h.length + 1)
