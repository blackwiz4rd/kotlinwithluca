package org.example.miscellaneous

class Numbers {
    interface Status {
        fun signal() {
            println("This is a signal")
        }
    }

    object Ok : Status
    object Maybe : Status
    object No : Status

    companion object {

        private const val MAX_BYTE: Byte = 127 // -128, 127
        private const val MAX_SHORT: Short = 32767 // -32768, 32767
        private const val MAX_INT = Int.MAX_VALUE
        private const val MAX_LONG = Long.MAX_VALUE
        private const val MAX_FLOAT = Float.MAX_VALUE
        private const val MAX_DOUBLE = Double.MAX_VALUE

        data class Color(val representation: UInt)

        private fun notExecuted(): Boolean {
            println("This function will not be executed")
            return false
        }

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

            // Lesson 5
            val unsignedByte: UByte = 23u
            println("unsignedByte (use it if you need more range with positives): ${unsignedByte}")

            val unsignedLong: ULong = 23uL
            println("unsignedLong (use it if you need more range with positives): ${unsignedLong}")

            val yellow = Color(0xFFCC00CCu)

            val byteArrayMarkUtf8 = ubyteArrayOf(0xFFu, 0xFAu)
        }

        fun playWithStrings() {
            val list = listOf<String>()
            println("list=${list} size=${list.size} lastIndex=${list.lastIndex}")

            println("true&&true=${true && true}, true and false=${true and false}, not true=${!true}")

            if (true || notExecuted()) {
                println("Entering this condition, without executing notExecuted() func")
            }

            if (false && notExecuted()) {
                println("Not entering this condition")
            } else {
                println("Entering this condition, without executing notExecuted() func")
            }

            val supportedSpecialCharaters: List<Char> = listOf(
                '\t', '\b', '\n', '\r', '\'', '\"', '\\', '\$', '\u00D1'
            )

            for (c in supportedSpecialCharaters) {
                println("special character = ${c}")
            }

            val characterNum = '1'
            println("given $characterNum as char, c.digitToInt()=${characterNum.digitToInt()}, c.code=${characterNum.code}")

            val s = "This is a string"
            println("All string characters")
            for (c in s) {
                println(c)
            }
            println(s.uppercase())
            println(s)
            println("Concatenation: (s + 1 + \" anotherString\")=${s + 1 + " anotherString"}")

            println(
                """
                                These are ${"\t"}multi
                                line ${'$'}${"\$"} strings
                        """.trimIndent()
            )

            println(
                String.format("this is a number with trailing 0: %06d ", 8)
            )

            println(
                String.format("this is a number with 4 floating points %+.4f", 3.141592)
            )

            println(
                String.format("this is a number with 4 floating points %+.4f", 3.141592)
            )

            println(
                String.format("this is a string to upper case: %S", "hello")
            )

            println(
                String.format(
                    "Negative number with parenthesis %(d, without parenthesis %1\$d, another number (two times): %2\$d %2\$d",
                    -234,
                    3
                )
            )
        }

        private fun printAllStrings(vararg stringContent: String) {
            for (s in stringContent) {
                print(s)
            }
            println()
        }

        fun playWithArrays() {
            val firstArray: IntArray = intArrayOf(0, 1, 4, 9, 16)
            val secondArray = arrayOfNulls<Int>(5)
            val thirdArray = Array<Int>(
                size = 5,
                init = { index ->
                    index * index
                }
            )
            val fourthArray = emptyArray<Int>()
            val fifthArray = Array<Int>(5) {
                3
            }

            fifthArray[4] = 10
            val sixthArray: IntArray = intArrayOf(0, 1, 4, 9, 16)

            val nestedArray = Array<Array<Int>>(2) {
                Array<Int>(2) { 0 }
            }

            nestedArray[0][1] = 2

            val anyTypeArray: Array<Any> = arrayOf("ciao", 2)

            println(firstArray.joinToString())
            println(secondArray.joinToString())
            println(thirdArray.joinToString())
            println(fourthArray.joinToString())
            println(fifthArray.joinToString())
            println(nestedArray.contentDeepToString())
            println(anyTypeArray.joinToString())

            val arrayStrings: Array<String> = arrayOf("4", "5")
            printAllStrings("2", "4", *arrayStrings)

            println("is it true? ${firstArray.contentEquals(thirdArray.toIntArray())}")
            println("is it false? ${firstArray contentEquals fifthArray.toIntArray()}")
            println("is it false? ${firstArray == thirdArray.toIntArray()}")

            println("sum on firstArray : ${firstArray.sum()}")
            firstArray.shuffle()
            println("shuffled firstArray : ${firstArray.joinToString()}")

            println("list ${firstArray.toList()}")
            println("set ${firstArray.toSet()}")

            println(
                "map ${
                    Array<Pair<Int, String>>(5) {
                        Pair<Int, String>(it, (it * it).toString())
                    }.toMap()
                }"
            )
        }

        private fun testPassingNumber(pi: Double) {
            println("${pi} ${pi.javaClass.name}")
        }

        fun playWithTypeCasts() {
            val s: String = "This is a string"
            val n: Int = 0
            val sNullable: String? = "Ciao"

            if (s is String) {
                println("The type of s is a String ${s.length}")
            }

            if (n !is String) {
                println("The type of n is not a String")
            }

            if (!(n is String)) {
                println("The type of n is not a String")
            }

            printLengthIfStringNotNull(s)
            printLengthIfStringNotNull(n)

            printLengthIfStringOrNumberIfInt(s)
            printLengthIfStringOrNumberIfInt(n)

            if (allowPrintingIfString(s)) {
                println("The type of s is a String ${s.length}")
            }
            if (allowPrintingIfString(n)) {
                println("The type of s is a String ${s.length}")
            }
            if (allowPrintingIfString("")) {
                println("The type of s is a String ${s.length}")
            }

            printLengthIfStringNotNull("")

            printSignalIfOkOrMaybe(Ok)
            printSignalIfOkOrMaybe(Maybe)
            printSignalIfOkOrMaybe(No)

            if (sNullable != null) {
                println("Nullable string ${sNullable} length=${sNullable.length}")
            }

            try {
                println("Trying to print s length ${(s as String).length}")
                println("Trying to print sNullable length ${(sNullable as String).length}")
            } catch (e: NullPointerException) {
                println("Code is not running: ${e}")
            }

            (sNullable as? String)?.let {
                println("Trying to print sNullable length ${(sNullable as String).length}")
            }

        }

        private fun printSignalIfOkOrMaybe(o: Any) {
            when {
                (o is Ok || o is Maybe) -> o.signal()
                else -> {
                    // do nothing
                }
            }
//                        if (o is Ok || o is Maybe) {
//                                o.signal()
//                        } else {
//
//                        }
        }

        private fun printLengthIfStringOrNumberIfInt(x: Any) {
            when (x) {
                is Int -> println("The value of this int is ${x}")
                is String -> println("The value of this String is \"\"${x}\"\" and its length is ${x.length}")
            }
        }

        private fun allowPrintingIfString(x: Any): Boolean = when {
            (x is String && x.length > 0) -> true
            else -> false
        }

        private fun printLengthIfStringNotNull(s: Any) {
            if (s !is String || s.length == 0) {
                println("Not a string or null string, we don't do anything")
                return
            }

            println("Since ${s} is a String, its length is ${s.length}")
        }
    }
}