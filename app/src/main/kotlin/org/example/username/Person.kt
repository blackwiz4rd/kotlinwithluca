package org.example.username

interface IPerson {
    fun printLength()
}

fun asInline(f: Person) {} // unboxed: used as Person itself
fun <T> asGeneric(x: T) {} // boxed: used as generic type T
fun asInterface(i: IPerson) {} // boxed: used as type IPerson
fun asNullable(i: Person?) {} // boxed: used as Person? != Person -> since it is nullable
// below, 'f' first is boxed (while being passed to 'id') and then unboxed (when returned from 'id')
// In the end, 'c' contains unboxed representation (just '42'), as 'f'
fun <T> id(x: T): T = x

@JvmInline
value class Person(val fullName: String): IPerson {
    init {
        require(fullName.isNotEmpty()) {
            "Full name is required"
        }
    }

    constructor(firstName: String, lastName: String) : this(firstName + " " + lastName) {
        require(lastName.isNotEmpty()) {
            "Last name must not be empty"
        }
    }

    val length: Int
        get() = fullName.length

    override fun printLength() {
        println("My length is: ${length}")
    }
}