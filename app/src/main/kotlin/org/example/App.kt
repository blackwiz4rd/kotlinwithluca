/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example

import org.example.computer.GeneralComputer
import org.example.computer.Laptop
import org.example.miscellaneous.Customer
import org.example.product.*
import org.secondary.*

fun main() {
//    // First lesson
//
//    firstLesson()

//    // Second lesson
//
//    secondLesson()

    // Third lesson
    // thirdLesson()

    // Fourth lesson
    // playWithNumbers()
    // Fifth lesson
    // playWithStrings()
    // Sixth lesson
    // playWithArrays()
    // Seventh lesson
    // playWithTypeCasts()
    // Eigth lesson
    //playWithConditions()
    // playWithLoops()
    // Ninth lesson
    // playWithExceptions()
    // Tenth lesson
    //playWithClasses()

    // Eleventh lesson
    // playWithClassInheritance()

    // Lesson twelve
    // playWithProperties()

    // Lesson thirteen
    //playWithFunctionalInterfaces()

    // visibility test
    //playWithVisibility()

    playWithExtensionFunctions()
}

fun playWithExtensionFunctions() {
    var productImpl: ProductImpl? = ProductImpl("Amatriciana", 1)
    productImpl?.addIngredient("Pasta")
    productImpl?.addIngredient("Sauce")
    productImpl?.addIngredient("Bacon")

    println("Before swap: ${productImpl?.ingredients}")
    productImpl.printSwapIngredients(0,2)
    println("After swap: ${productImpl?.ingredients}")
    println(productImpl)
    println(productImpl == productImpl?.copy())
    println(productImpl == productImpl?.copy(productId = 5))
    print(productImpl?.copy(productId = 4))

    productImpl = null
    productImpl.printSwapIngredients(0,2)

    val testProductImpl = Product.createProductTest()

    val (name, productId) = testProductImpl

    println(testProductImpl.component1())
    println(testProductImpl.component2())

    println("desctructing declaration: ${name}, ${productId}")

    Pair<Int,String>(1,"ciao")
    Triple<Int,String,Int>(1,"ciao",4)
}

fun playWithVisibility() {
    createEv()
}

fun firstLesson() {
    val myLaptop = Laptop(2, 3, true)
    println(myLaptop)
    myLaptop.printManufacturers()

    println(Laptop(2, 3, true, "acer"))
    println(Laptop(2, 3, true))
    println(myLaptop.manufacturer == null)
}

fun secondLesson() {
    val customer = Customer("Mario", "m.rossi@example.com", "Rossi")
    println("Email ${customer.email}")
    println("Name ${customer.name}")
    println("Surname ${customer.surname}")
    customer.surname = "Bianchi"
    println("$customer")

    val customer2 = customer.copy(
        name = "Giovanni"
    )
    val customer3 = customer.copy()

    // with data classes
    println("Comparing data classes ${customer == customer3}")

    // with normal class
    println("Comparing two normal classes ${Laptop(2, 3, true) == Laptop(2, 3, true)}")

    val laptop = Laptop(manufacturer = "acer")
    println(laptop.possibleManufacturers.filter { it?.startsWith("a") ?: false })
    println("benq" in laptop.possibleManufacturers)
    println("lenovo" in laptop.possibleManufacturers)

    println("ciao".toIntOrNull())
    println("3".toIntOrNull())

    val i =
        when ("3".toIntOrNull()) {
            is Int -> println("We are now sure it's an integer")
            else -> println("This is not an integer")
        }

    println("${laptop.possibleManufacturersCodes}")
    println("${laptop.possibleManufacturersCodes["acer"]}")
    println("${laptop.possibleManufacturersCodes.keys}")
    println("${laptop.possibleManufacturersCodes.values}")

    for ((k, v) in laptop.possibleManufacturersCodes) {
        println("k=${k} v=${v}")
    }

    println(laptop.builtInManufacturer)
    println(laptop.possibleManufacturersCameCase)
    println(laptop.possibleManufacturers.firstOrNull())
    println(laptop.possibleManufacturers.lastOrNull())

    println(GeneralComputer.capitalize("hello"))

    try {
        // tons of code in here!!
        //
        //

        println(listOf<String>().last())
    } catch (e: NoSuchElementException) {
        println("No element in the list, the list is empty!")
    }
    println(listOf<String>().lastOrNull() ?: "No element in the list, the list is empty!")

    println()

    with(laptop) {
        println(possibleManufacturers)
        println(printManufacturers())
    }

    with(customer) {
        surname = "Gino"
        println(this)
    }

    customer
        /**
         * Purpose: Used to configure or initialize an object.
         * Receiver (this): Refers to the object itself within the block.
         * Return value: Returns the original object (the receiver).
         */
        .apply {
            surname = "Biden"
            println(surname)
        }
        /**
         * Purpose: Used to perform additional actions on an object without modifying it (e.g., logging, debugging, etc.).
         * Receiver (it): Refers to the object itself as it within the block.
         * Return value: Returns the original object (the receiver).
         */
        .also {
            it.surname = "test"
            println(it)
            //println(it.copy(name = "Paolo"))
        }

    println(customer)

    try {
        customer.takeABeer()
    } catch (e: NotImplementedError) {
        println("Hello, no beer")
    }
}

fun thirdLesson() {
    val productImpl = ProductImpl("apple")
    val secondProductImpl = ProductImpl("eggs", "boils some water!")
    println(secondProductImpl)
    secondProductImpl.`this is a test method`()

    secondProductImpl.addIngredient("yolk")
    println(secondProductImpl.ingredients)
}
