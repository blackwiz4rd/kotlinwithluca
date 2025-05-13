package org.example.func

open class A {
    open fun foo(i: Int = 10) {
        println(i)
    }
}

infix fun Int.multiply(i: Int): Int {
    return this*i
}

fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

class B(private val passed: Int) : A() {

    infix fun multiply(i: Int): Int {
        return passed*i
    }

    fun printMultiplyWithInfix(x: Int) {
        println(passed multiply x)
    }

    override fun foo(i: Int) {
        val moddedI = i + 10

        fun printThatStuff() {
            val x = 0 // not seen in foo
            println("foo output is $moddedI")
        }

        printThatStuff()
    }  // No default value is allowed.

    fun double(i: Int): Int {
        return i*2
    }

    fun doubleInSingleExpression(i: Int): Int = i * 2
    fun doubleInSingleExpressionOptionalReturn(i: Int) = i * 2

    fun multiply(
        a: Int = 2,
        b: Int = 3
    ): Int = a * b

    fun anotherFoo(bar: Int = 0, coffe: Int) {
        println("anotherFoo output is ${bar*coffe}")
    }

    fun anotherFooSample(coffe: Int, something: Int = 3, runAfterPrint: () -> Unit) {
        // not easy to determin the return type in complex functions
        println("anotherFoo output is ${coffe*something}")
        runAfterPrint()
    }

    fun dontMessUpWithNames(s: String, upperCase: Boolean,  addSuffix: Boolean, suffix: String = "-suffix", separator: String = "_", vararg separatedStrings: String) {
        var result = s
        if (upperCase) {
            result = result.uppercase()
        }
        if (addSuffix) {
            result += suffix
        }
        var newSeparated = ""
        separatedStrings.forEach {
            newSeparated += it + separator
        }

        println(result)
        println(newSeparated.removeSuffix("_"))
    }
}