package org.secondary

open class User(open val name: String) {
    open fun customPhraseName(): String {
        return "My name is ${name}"
    }
}

data class Customer(
    val customerName: String
) : User(customerName) {
    override fun customPhraseName() : String {
        this.name
        this.customerName
        return "${this.name} = ${super.customPhraseName()}"
    }
}

data class Empty(val test: String) : Any() {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "Empty class = ${super.toString()}"
    }
}

internal fun printCustomer() {
    val customer = Customer("test name")
    println("Hello I'm customer $customer")
}