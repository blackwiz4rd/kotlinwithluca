package org.example.generics

// from: Array<out Any> means that from will be read only
fun copy(from: Array<out Any>, to: Array<Any>) {
    for (i in from.indices) {
        if (i < to.size) {
            to[i] = from[i] // Safe because `to` is an Array<Any>
        }
    }
}

fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

fun <T> T.basicToString(): String { // extension function
    if (this is List<*>) {
        return "listOf(${this})"
    } else {
        return "$this"
    }
}