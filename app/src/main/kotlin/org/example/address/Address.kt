package org.example.address

data class Address(
    var name: String = "test",
    var street: String,
    var city: String,
    var state: String?,
    var zip: String,
) {
    var initilized = 1
    val aboveHundred: Boolean

    init {
        aboveHundred = (zip.toIntOrNull() ?: 0) > 100
    }
}