/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example

import Password
import PasswordAlias
import TestComparable
import TestComparableImpl
import callSdkExample
import org.example.api.ApiRequest
import org.example.computer.GeneralComputer
import org.example.computer.Laptop
import org.example.error.AppIError
import org.example.error.IOIError
import org.example.miscellaneous.Customer
import org.example.product.*
import org.secondary.*
import org.example.state.UIState
import org.example.api.User
import org.example.box.Box
import org.example.custom.CustomEvent
import org.example.custom.CustomEventListener
import org.example.custom.CustomViewModel
import org.example.enums.Direction
import org.example.generics.basicToString
import org.example.generics.copy
import org.example.generics.singletonList
import org.example.notification.Notification
import org.example.notification.NotificationManager
import org.example.source.Source
import org.example.source.SourceImpl
import org.example.username.IPerson
import org.example.username.Person
import org.example.username.testUserCreation
import org.jetbrains.annotations.TestOnly
import java.lang.IllegalArgumentException
import java.util.Collections.sort

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

    // playWithExtensionFunctions()

    // playWithSealedClasses()

    // lesson on Generics
    //playWithGenerics()

    // Nested, Inner, Enum, Inline values classes
    moreOnClasses()

    // inline values
    // inlineValuesTesting()

    // object declarations and expressions
    objectDeclarations()
}

fun objectDeclarations() {
    println(org.example.username.SocialNumber == org.example.username.SocialNumber)
    println(testUserCreation())
    val notification = NotificationManager()

    notification.getEmailNotification()
    notification.getNotification()


    (notification.getDetailedNotification() as Notification).notifyUser()
    (notification.getDetailedNotification() as Notification).notifyUser()
}

fun inlineValuesTesting() {
    val securePassword = Password("ciao")

    val person1 = Person("Luca")
    val person2 = Person("Luca", "Me")

    println(person1)
    println(person2)
    try {
        val person3 = Person("Luca", "")
        println(person3)

    } catch (e: IllegalArgumentException) {

    }

    letMePrintLength(person1)

    val pAlias: PasswordAlias = "PasswordAlias"
    val p = Password("PasswordInlineValue")

    passPasswordAlias(pAlias)
    passPasswordString(pAlias)
    // passPasswordString(p) not allowed
    passPasswordInlineValue(p)

    callSdkExample()
}

fun passPasswordInlineValue(s: Password) {
    println(s)
}

fun passPasswordAlias(s: PasswordAlias) {
    println(s)
}

fun passPasswordString(s: String) {
    println(s)
}

fun letMePrintLength(person: IPerson) {
    person.printLength()
}

fun moreOnClasses() {
    val viewModel = CustomViewModel()
    println("viewModel.state=${viewModel.state}")
    viewModel.loadUi()
    println("viewModel.state=${viewModel.state}")
    println("Check password ${viewModel.checkPassword()}")

    // useful to capture results in our main function
    viewModel.addEventListener(object : CustomEventListener {
        override fun onEvent(customEvent: CustomEvent) {
            when (customEvent.eventType) {
                "test1" -> {
                    sideEffectCalulated += 1
                    println("we are capturing result for test1")
                }
                "test2" -> {
                    sideEffectCalulated += 2
                    println("we are capturing result for test2")
                }
            }
        }

        override var sideEffectCalulated: Int = 0
    })

    viewModel.test1()
    viewModel.test2()
    viewModel.test1()
    println("outputOfSideEffect=${viewModel.outputOfSideEffect()}")

    viewModel.testCustomEventPass(CustomEvent("test1"))
    viewModel.testCustomEventPass(CustomEvent("test1"))
    println("outputOfSideEffect=${viewModel.outputOfSideEffect()}")
    viewModel.moveShip(Direction.EAST)
    viewModel.moveShip(Direction.NORTH)

    println("plus 3,7 ${viewModel.plusOperation(3,7)}")
    println("times 4,5 ${viewModel.timesOperation(4,5)}")
}

fun demoGenerics(strs: Source<String>) {
    val objects: Source<Any> = strs // subtyping is preserved (so this is allowed because we are using out in Source<String>)
    val strsCopy: Source<String> = objects as Source<String>
    println(strsCopy)
}

fun playWithGenerics() {
    val box: Box<Int> = Box(1)
    val box2 = Box(2L)
    val sources = SourceImpl<String>()
    sources.initElements(listOf("a","b","c"))
    demoGenerics(sources)

    demo(TestComparableImpl<Int>(2) as TestComparable<Number>)

    val source: Array<String> = arrayOf("Hello", "World")
    val destination: Array<Any> = arrayOf(1,2,3)

    copy(source, destination)
    println(destination.joinToString())

    println("List of generics")
    println(singletonList(1).basicToString())
    println(singletonList("ciao").basicToString())
    println("testString".basicToString())

    val a = listOf(3,2,1)
    println(a.sorted())
    //println(HashMap<Int, String>().sorted())
}

fun demo(x: TestComparable<Number>) {
    println("comparison: ${x.compareTo(1)}")
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: TestComparable<Double> = x // OK!
    println(y)
}

fun playWithSealedClasses() {
    val productExtras = ProductImpl("Amatriciana", 1)
    productExtras.test()

    var state: UIState = UIState.Loading
    // CustomState() // CustomState is abstract and cannot be instanciated
    updateUiState(state)
    state = UIState.GoBack
    updateUiState(state)

    listOf(AppIError.DatabaseIError(true), AppIError.DatabaseIError(false), AppIError.NetworkIError(), AppIError.UnknownIError()).forEach {
        println("Message: ${it.message} ${it.severity}")
        println("Should I retry on this error? ${checkErrorRetry(it)}")
    }

    // call the request
    val user = User(1, "validUser", "validPass")
    println(user.handleRequest(ApiRequest.LoginRequest))
    println(user.isLogged)
    println(user.handleRequest(ApiRequest.LogoutRequest))
    println(user.isLogged)
    println(User(1, "test", "test").handleRequest(ApiRequest.LoginRequest))
    println(user.isLogged)
}

fun updateUiState(state: UIState) {
    when (state) {
        UIState.GoBack -> println("Going back in screen")
        UIState.Loading -> println("Loading screen")
        is UIState.Success -> println("Success")
        UIState.UiLoaded -> println("UI Loaded")
        is UIState.UiError -> println("Error ongoing")
    }
}

fun checkErrorRetry(e: AppIError): Boolean {
    return when (e) {
        is AppIError.DatabaseIError -> if (e.databaseCorrupted) false else true
        is AppIError.NetworkIError -> true
        is AppIError.UnknownIError -> false
    }
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
