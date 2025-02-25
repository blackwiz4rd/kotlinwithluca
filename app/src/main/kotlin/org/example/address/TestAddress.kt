package org.example.address

class TestAddress {
    lateinit var address: Address

    fun setup() {
        address = Address(
            name = "John Doe",
            street = "123 Main St",
            city = "New York",
            state = "NY",
            zip = "10001"
        )
    }

    fun test() {
        assert(address.aboveHundred)
        println("Test OK")
    }
}