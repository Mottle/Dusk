package moe.liar.dusk.utils

typealias Multimap<T, V> = MutableMap<T, MutableSet<V>>

fun <T, V> multimapOf(vararg entry: Pair<T, V>): Multimap<T, V> {
    val multimap = mutableMapOf<T, MutableSet<V>>()
    entry.forEach {
        if(multimap.containsKey(it.first)) {
            val set = multimap[it.first] as MutableSet<V>
            set.add(it.second)
        } else multimap[it.first] = mutableSetOf()
    }
    return multimap
}