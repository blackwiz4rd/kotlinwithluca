package org.secondary

// they create a new type
fun interface IntPredicate : IntPredicateExtension {
    fun accept(i: Int): Boolean
 }

interface IntPredicateExtension {
    fun printing(i: Int): String {
        return "Your number is ${i}"
    }
}

// name for existing type
typealias IntPredicateAlias = (i: Int) -> Boolean