package org.example.computer

class Desktop(override val height: Int, override val width: Int) :
    GeneralComputer(height, width, "acer") {
    override fun toString(): String {
        return getPrintString("Desktop")
    }
}