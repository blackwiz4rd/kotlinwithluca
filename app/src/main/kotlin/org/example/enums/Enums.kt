package org.example.enums

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

enum class Direction(val points: Int) {
    NORTH(1) {
        fun ayeCaptain() {
            println("Going north, easy with the oars")
        }

        override fun signal() {
            println("signal sent")
        }
    }, SOUTH(2) {
        override fun signal() {
            TODO("Not yet implemented")
        }
    }, WEST(3) {
        override fun signal() {
            TODO("Not yet implemented")
        }
    }, EAST(4) {
        override fun signal() {
            TODO("Not yet implemented")
        }
    };

    abstract fun signal()
    fun saySomething() {
        println("say something")
    }

    val isNorthEast
        get() = this == NORTH || this == EAST
}

enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}