package org.example.product

interface Product {
    fun deleteProductDescription()
    fun changeWeight(weight: ProductImpl.Weight)
    fun changeWeight(weightValue: Int)
}
