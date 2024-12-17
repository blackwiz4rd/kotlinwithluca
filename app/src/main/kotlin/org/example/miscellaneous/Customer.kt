package org.example.miscellaneous

data class Customer(val name: String, val email: String, var surname: String) {
    override fun toString(): String {
        return "Customer is $name $surname and has an email=$email"
    }

    fun takeABeer() {
        TODO("This function is not yet completed")
    }
}