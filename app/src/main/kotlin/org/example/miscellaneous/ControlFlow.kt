package org.example.miscellaneous

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class ControlFlow {
    companion object {

        sealed class Bit(i: Int) {
            data object One: Bit(0)
            data object Zero: Bit(1)

            internal val b = if (i == 0) false else true
        }

        enum class TrafficLight(val stopped: Boolean = true) {
            RED,YELLOW,GREEN(false),UNKNWON,BROKEN,NO_CONNECTION
        }

        open class Vehicle(
            open val hasWindows: Boolean = true
        )

        data class Car(
            override val hasWindows: Boolean = true
        ) : Vehicle(
            hasWindows
        ) {
            internal var moving: Boolean = false

            fun startCar() {
                moving = true
            }
            fun stopCar() {
                moving = false
            }

            @Throws
            fun cleanItself() {
                TODO("Cleaning is not implemented")
            }
        }

        fun playWithConditions() {
            // let's play with if
            val a = 3
            val b = 44
            val limit = 10
            println("Max value between ${a} and ${b} (overall limit to ${limit}) is ${takeMaxValueWithLimit(a,b,limit)}")
            println("Max value between ${a} and ${b} (overall limit to ${limit}) is ${takeMaxValueWithLimitWithWhen(a,b,limit)}")

            println()

            runConditionOnTrafficLightColor(TrafficLight.GREEN)

            runConditionOnBit(Bit.One)

            runConditionOnBooleans(Bit.One.b)

            runConditionIfInsideRanges(2)

            runConditionBasedOnType("Ciao")
            runConditionBasedOnType(3)
        }

        private fun runConditionBasedOnType(x: Any) {
            when (x) {
                is String -> println("x is a String: ${x}, startsWithC=${x.startsWith("C")}")
                is Int -> println("x is a Int: ${x*10}")
            }
        }

        private fun runConditionIfInsideRanges(i: Int) {
            when (val x = i*10) {
                in 1..10 -> println("Inside 1..10 range x=${x}")
                in 1..15 -> println("Inside 1..15 range x=${x}")
                !in 1..15 -> println("Not Inside 1..15 range x=${x}")
                else -> {

                }
            }
        }

        private fun runConditionOnBit(bit: Bit) {
            when (bit) {
                // guard condition example: is Bit.One if bit.i == 1
                is Bit.One -> println("This is a one")
                is Bit.Zero -> println("This is a zero")
            }
        }


        private fun runConditionOnBooleans(b: Boolean) {
            when (b) {
                true -> println("This is a one")
                false -> println("This is a zero")
            }
        }


        private fun runConditionOnTrafficLightColor(trafficLightColor: TrafficLight): Boolean {
            val stopped: Boolean = when (trafficLightColor) {
                TrafficLight.RED -> {
                    println("You cannot proceed")
                    true
                }
                TrafficLight.YELLOW -> {
                    println("You might think that you can proceed soon!")
                    true
                }
                TrafficLight.GREEN -> {
                    println("Go as fast as you can!")
                    false
                }

                TrafficLight.UNKNWON,
                TrafficLight.BROKEN,
                TrafficLight.NO_CONNECTION -> false
            }

            return stopped
        }

        private fun takeMaxValueWithLimitWithWhen(a: Int, b: Int, limit: Int): Int {
            return when {
                (a > limit).or(b > limit) -> {
                    limit
                }

                (a > b) -> {
                    a
                }

                else -> {
                    b
                }
            }
        }

        private fun takeMaxValueWithLimit(a: Int, b: Int, limit: Int): Int {
            val max = if ((a > limit).or(b > limit)) {
                println("The max limit is reached")
                limit
            } else if (a > b) {
                a
            } else {
                b
            }

            if ((a > limit).or(b > limit)) {
                println("The max limit is reached, no need for else branch")
            }

            return max
        }

        fun playWithLoops() {
            cycleInCollection()
            cycleInList()

            whileLoopExample()

        }

        private fun whileLoopExample() {
            var x = -1
            while (x > 0) {
                x -= 1
            }
            println("After cycle x = ${x}")

            x = -1

            do {
                x -= 1
            } while (x > 0)

            println("After cycle x = ${x}")
        }

        private fun cycleInList() {
            // example of continue
            (listOf("a","c","d","c").withIndex()).forEach lit@{
                if (it.value == "c") {
                    return@lit
                }
                println("index=${it.index}, value=${it.value}")
            }

            // example of continue
            (listOf("a","c","d","c").withIndex()).forEach{
                if (it.value == "c") {
                    return@forEach
                }
                println("index=${it.index}, value=${it.value}")
            }

            // example of break
            val isBroken = run breakLoop@{
                (listOf("a","c","d","c").withIndex()).forEach{
                    if (it.value == "c") {
                        return@breakLoop true
                    }
                    println("index=${it.index}, value=${it.value}")
                }
                return@breakLoop false
            }

            println("The break condition is met? $isBroken")
        }

        private fun cycleInCollection() {
            loop@ for (i in 1..10) {
                if (i == 3)
                    continue@loop

                if (i > 5) {
                    break@loop
                }
                println(i)
            }
        }

        fun playWithExceptions() {
            val a = 4
            when (a) {
                in 1..10 -> {
                    println("Now the code runs without exception because we are in proper range 1..10")
                }
                !in 1..10 -> throw IllegalArgumentException("The number you entered is in not in range from 1..10")
            }

            // check that enumerators are set properly
            testTrafficLightIndicators()

            val trafficLightIndicator = TrafficLight.GREEN
            val car = Car()

            if (trafficLightIndicator == TrafficLight.UNKNWON) {
                failWithUnknownIndicator()
            }

            @Suppress("USELESS_IS_CHECK")
            require(car is Vehicle)

            car.startCar()

            println("Car is moving: ${car.moving}, trafficLight is ${trafficLightIndicator}")

            val takesFine = try {
                when (trafficLightIndicator) {
                    TrafficLight.GREEN -> check(car.moving == true) { "The drive will not get a fine, but he must move" }
                    else -> check(car.moving == false) { "The driver might get a fine" }
                }
                false
            } catch (e: IllegalStateException) {
                println("The code is throwing ${e}")
                true
            } catch (e: RuntimeException) {
                println("The code is throwing (RuntimeException) ${e}")
                true
            } catch (e: Exception) {
                println("The code is throwing ${e}")
                true
            } finally {
                println("Imagine we have a 2s delay and we want to stop the car then")
                car.stopCar()
            }

            // since it's an error we should not catch it but comment it
            //car.cleanItself()

            try {
                val example = 2/0
                println(example)
            } catch (e: ArithmeticException) {
                println("${e}")
            }

            try {
                val l = mutableListOf(1,2,3,4)
                if (l.getOrNull(3) != null) {
                    l.removeAt(3)
                    println("Successfully removed the element ${l}")
                } else {
                    println("No need to handle exception, third element does not exist")
                }
            } catch (e: IndexOutOfBoundsException) {
                println("${e}")
            }

            try {
                println("elment of null list: ${listOf<Int>().firstOrNull()}")
                println("element of list ${listOf<Int>().first()}")
            } catch (e: NoSuchElementException) {
                println("No element in list")
            }

            try {
                val n = "ciao".toIntOrNull()
                println("Switching from string to int safely: ${n}")
                if (n != null) {
                    if (n!! > 0) {
                        println("Greater than zero")
                    }
                }
                println("Switching from string to int is not possible ${"ciao".toInt()}")
            } catch (e: NumberFormatException) {
                println("Impossible to change string to int")
            }

        }

        private fun failWithUnknownIndicator(): Nothing {
            throw TrafficLightException.UnknownIndicatorException()
        }

        sealed class TrafficLightException(e: String) : Exception(e) {
            class IllegalTrafficLightIndicatorException: TrafficLightException("Traffic light indicator is illegally setup")
            class UnknownIndicatorException: TrafficLightException("The indicator is unknown")
        }


        private fun testTrafficLightIndicators() {
            TrafficLight.entries.filter { it != TrafficLight.UNKNWON }.forEach {
                if (it.stopped == false && it != TrafficLight.GREEN) {
                    throw TrafficLightException.IllegalTrafficLightIndicatorException()
                } else if (it == TrafficLight.UNKNWON) {
                    throw TrafficLightException.UnknownIndicatorException()
                }
            }
            println("All is good with traffic light entries")
        }
    }
}