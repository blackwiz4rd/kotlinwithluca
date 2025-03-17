package org.example.product

import jdk.jfr.Name
import org.example.error.AppIError
import org.jetbrains.annotations.TestOnly

/**
 * This is a product class to store information about the product
 *
 * @property name
 */

data class ProductImpl(val name: String, val productId: Int = 0) : ProductExtras {
    enum class Unit {
        KG, LBS
    }

    data class Weight(var value: Int, val unit: Unit)

    @Name("Weight")
    private var weight: Weight? = null
    var isAdded: Boolean
    private var description: String? = null

    private val _ingredients: MutableList<String> = mutableListOf()
    override fun test() {
        println("This is a test")
    }

    override val ingredients: List<String>
        get() = _ingredients

    private val personComparator: Comparator<String> =
        Comparator<String> { o1, o2 -> o1.length - o2.length }

    init {
        println("Initilized")
        isAdded = false
    }

    constructor(name: String, description: String?) : this(name) {
        println("Running secondary after primary constructor")
        this.description = description
    }

    override fun deleteProductDescription() {
        deleteProductDescriptionWarning()
        this.description = null
    }

    private fun deleteProductDescriptionWarning() {
        println("We are now deleting the product description")
    }

    override fun changeWeight(weight: Weight) {
        this.weight = weight
    }

    override fun changeWeight(weightValue: Int) {
        this.weight?.apply {
            value = weightValue
        } ?: run {
            // create weight if the weight is not defined yet
            this.weight = Weight(weightValue, Unit.KG)
        }

    }

    fun checkLengthDifferenceBetweenDescriptions(description: String) {
        personComparator.compare(this.description, description)
    }

    override fun toString(): String {
        return "TAG=$TAG, Product" +
                "(name=${name}," +
               "description=${description}," +
               "productId=${productId}" +
               ")\n"
    }

    fun addIngredient(s: String) {
        _ingredients.add(s)
    }

    override fun replaceAllIngredients(ingredients: List<String>) {
        _ingredients.removeAll { true }
        _ingredients.addAll(0, ingredients)
    }

    @TestOnly
    fun `this is a test method`() {
        changeWeight(Weight(1, Unit.KG))
        println(this.weight)
        assert(this.weight == Weight(1, Unit.KG))
        // TODO: check why assertion is not triggering the exception
    }

    companion object {
        const val TAG = "product"
    }
}

fun ProductImpl.addProduct() {
    isAdded = true
}