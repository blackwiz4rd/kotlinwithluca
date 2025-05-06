package org.secondary

import java.io.File

// they create a new type
fun interface IntPredicate : IntPredicateExtension {
    fun accept(i: Int): Boolean
 }

interface IntPredicateExtension {
    fun printing(i: Int): String {
        return "Your number is ${i}"
    }
}

// name for existing type (useful to reduce length of fields like in case below)
typealias FileTable<K> = MutableMap<K, MutableList<File>>
// it works with function return types
typealias Predicate<T> = (T) -> Boolean
typealias IntPredicateAlias = (i: Int) -> Boolean