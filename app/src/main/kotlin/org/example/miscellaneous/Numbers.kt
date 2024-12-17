package org.example.miscellaneous

class Numbers {
        companion object {

                private const val MAX_BYTE: Byte = 127 // -128, 127
                private const val MAX_SHORT: Short = 32767 // -32768, 32767
                private const val MAX_INT = Int.MAX_VALUE
                private const val MAX_LONG = Long.MAX_VALUE
                private const val MAX_FLOAT = Float.MAX_VALUE
                private const val MAX_DOUBLE = Double.MAX_VALUE


                fun playWithNumbers() {
                        val bytePlusOne = MAX_BYTE + 1
                        println("Computation on 127 as byte + 1 ${bytePlusOne} ${bytePlusOne.javaClass.name}")
                        val maxBytePlusOneToShort: Short = (MAX_BYTE + 1).toShort()
                        println("Computation on 127 as byte + 1 ${maxBytePlusOneToShort} ${maxBytePlusOneToShort.javaClass.name}")
                        @Suppress("INTEGER_OVERFLOW") val integerOverflow = MAX_INT + 1
                        println("Computation on Int.MAX_VALUE + 1 (overflow) ${integerOverflow} ${integerOverflow.javaClass.name}")
                        val longFromInt: Long = MAX_INT + 1L
                        println("Computation on Int.MAX_VALUE + 1L ${longFromInt} ${longFromInt.javaClass.name}")
                        @Suppress("INTEGER_OVERFLOW") val longOverflow = MAX_LONG + 1L
                        println("Computation on Long.MAX_VALUE + 1 (overflow) ${longOverflow} ${longOverflow.javaClass.name}")

                        val pi = 3.14
                        println("Double ${pi} ${pi.javaClass.name}")

                        val f = 3.14f
                        println("Float ${f} ${f.javaClass.name}")

                        testPassingNumber(pi)

                        val hexValue = 0x04
                        val binValue = 0b100

                        val doubleValueWithExpNotation = 1.3e4 // 1.3*10**4

                        val oneMillion = 1_000_000

                        println("hexValue=${hexValue} binValue=${binValue} doubleValueWithExpNotation=${doubleValueWithExpNotation} oneMillion=${oneMillion}")

                        // TODO: is there a better explaination?
                        // a has an address in memory of 0xa, here the reference is 0xa
                        // boxedA has an address in memory of 0xb and 0xb->0xa, here the reference is 0xa
                        // anotherBoxedA has an address in memory of 0xc and 0xc->0xa, here the reference is 0xa

                        val aInt: Int = 10000
                        val boxedA: Int? = aInt
                        val anotherBoxedA: Int? = aInt

                        println("Class of a=${aInt.javaClass.name} boxedA=${boxedA?.javaClass?.name} anotherBoxedA=${boxedA?.javaClass?.name}")
                        println("Value of a=${aInt} boxedA=${boxedA} anotherBoxedA=${anotherBoxedA}")
                        println(boxedA === anotherBoxedA)
                        println(aInt === anotherBoxedA)
                        println(aInt === boxedA)

                        // Hypothetical code, does not actually compile:
                        val a: Int? = 1 // A boxed Int (java.lang.Integer)
                        val b: Long? = a?.toLong() // Implicit conversion yields a boxed Long (java.lang.Long)
                        println(b == a?.toLong()) // Surprise! This prints "false" as Long's equals() checks whether the other is Long as well

                        val divisionInteger = 5 / 2
                        println("Divide 5 by 2 (int) = $divisionInteger")

                        val divisionLong = 5L / 2
                        println("Divide 5 by 2 (long) = $divisionLong")

                        val divisionDouble: Double = 5.0 / 2
                        println("Divide 5 by 2 (double) = $divisionDouble")

                        // 0b10 and 0b11
                        println("Bitwise operation 2 and 3 = 2 ${0b10 and 0b11}")

                        // strange things
                        println("Strange things -0.0 < 0.0: ${-0.0 < 0.0}")
                        println("Good things -0.0 == 0.0: ${-0.0 == 0.0}")
                        println("Good things null == null ${null == null}")
                        val i: Int? = null
                        // Operand statically typed as floating-point number
                        println(listOf(Double.NaN, Double.POSITIVE_INFINITY, 0.0, -0.0).sorted())
                }

                private fun testPassingNumber(pi: Double) {
                        println("${pi} ${pi.javaClass.name}")
                }
        }
}