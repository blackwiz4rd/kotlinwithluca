package org.example.computer

class Laptop(
    override val height: Int = 200,
    override val width: Int = 100,
    private val hinge: Boolean = false,
    override val manufacturer: String? = null
) : GeneralComputer(height, width, manufacturer) {
    override fun toString(): String {
        return getPrintString("Laptop")
    }
}