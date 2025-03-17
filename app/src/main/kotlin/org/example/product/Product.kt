package org.example.product

interface Product {

    companion object {}

    val ingredients: List<String>
    fun deleteProductDescription()
    fun changeWeight(weight: ProductImpl.Weight)
    fun changeWeight(weightValue: Int)

    fun replaceAllIngredients(ingredients: List<String>)
}
