interface Base {
    val message: String
    fun print()
    fun printMessageLine()
}

class BaseImpl(private val x: Int) : Base {
    override val message = "BaseImpl: x = $x"
    override fun print() { println(message) }
    override fun printMessageLine() { println("line: x = $x\n") }

}

class Derived(b: Base) : Base by b {
    // This property is not accessed from b's implementation of `print`
    override val message = "Message of Derived"

    // override test
    override fun printMessageLine() { println("overriding print") }

}