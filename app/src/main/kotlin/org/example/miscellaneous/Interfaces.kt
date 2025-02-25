package org.example.miscellaneous

interface IRectangle {
    val width: Int
    val height: Int
    fun bar()
    fun foo() {}

    fun calculateArea() = this.width * this.height

    val area: Int // property type is optional since it can be inferred from the getter's return type
        get() = this.width * this.height

    companion object {
        const val DEPRECATION_TEXT = "This function is no longer suggested"
    }
}