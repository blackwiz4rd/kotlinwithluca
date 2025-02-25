package org.secondary

import org.example.miscellaneous.IRectangle
import org.example.miscellaneous.IRectangle.Companion.DEPRECATION_TEXT

interface Polygon {
    fun draw() {
        println("Hello I'm a polygon")
    } // interface members are 'open' by default
}

open class Rectangle(override var height: Int, override var width: Int) : IRectangle {

    open fun draw() { println(textInside) }
    val borderColor: String get() = "black"
    val areaBad: Int = this.width * this.height

    var textInside: String? = null
        get() = "Drawing a ${toString()}: $field"
        private set(value) {
            println("New value is ${value}, old: ${textInside}")
            field = value
        }

    fun newTextInside(newTextInside: String) {
        textInside = newTextInside
    }

    @Deprecated(DEPRECATION_TEXT, ReplaceWith(".area"))
    override fun bar() {
        println("Bar")
    }

    override fun foo() {
        println("Foo")
        super.foo()
    }

    override fun toString(): String {
        return "Rectangle ${height} x ${width}"
    }
}

class FilledRectangle(override var height: Int, override var width: Int): Rectangle(height, width) {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun fill() { println("Filling") }
        fun drawAndFill() {
            super@FilledRectangle.draw() // Calls Rectangle's implementation of draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // Uses Rectangle's implementation of borderColor's get()
        }
    }
}

class Square(size: Int): Rectangle(size, size), Polygon {
    override fun draw() {
        super<Rectangle>.draw()
        super<Polygon>.draw()
    }
}