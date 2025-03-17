package org.example.product

import java.lang.IllegalArgumentException

fun Product.Companion.createProductTest(): ProductImpl {
    val test = ProductImpl("test", 2)
    test.addIngredient("TestIngredient")
    println("All our test ingredients are: ${test.ingredients} (${test.ingredientsSize})")
    return test
}
val Product.ingredientsSize: Int
    get() = this.ingredients.size

fun Product?.printSwapIngredients(index1:Int, index2:Int) {
    if (this == null) {
        println("No ingredients swapped, because product is null")
    } else {
        swapIngredients(index1, index2)
        println("Swapped ingredients: ${ingredients} (${ingredientsSize})")
    }
}

fun Product.swapIngredients(index1: Int, index2: Int) {
    if (index1 < this.ingredients.size && index2 < this.ingredients.size) {
        val tempList = this.ingredients.toMutableList()
        tempList.swap(index1, index2)
        replaceAllIngredients(tempList)
    } else {
        throw IllegalArgumentException()
    }
}

fun Product.changeWeight(weightUnit: Unit) {
    // TODO: change weight unit
}


// generic implementation
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
