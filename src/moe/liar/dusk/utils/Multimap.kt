package moe.liar.dusk.utils

typealias MultiMap<T, V> = MutableMap<T, MutableSet<V>>

fun <T, V> multiMapOf(vararg entry: Pair<T, V>): MultiMap<T, V> {
    val multiMap = mutableMapOf<T, MutableSet<V>>()
    entry.forEach {
        if (multiMap.containsKey(it.first)) {
            val set = multiMap[it.first] as MutableSet<V>
            set.add(it.second)
        } else multiMap[it.first] = mutableSetOf(it.second)
    }
    return multiMap
}

fun <T, V> MultiMap<T, V>.add(key: T, value: V) {
    if (this.containsKey(key)) (this[key] as MutableSet).add(value)
    else this[key] = mutableSetOf(value)
}