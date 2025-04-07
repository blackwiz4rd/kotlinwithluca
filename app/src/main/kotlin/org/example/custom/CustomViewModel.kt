package org.example.custom

import org.example.enums.Direction
import org.example.enums.IntArithmetics
import kotlin.enums.enumEntries

class CustomEvent(val eventType: String)

interface CustomEventListener { // interface that needs to be implemented (e.g. by our object in the viewmodel)
    fun onEvent(customEvent: CustomEvent) // callback

    var sideEffectCalulated: Int
}

class CustomViewModel {

    private val username: String = "luca"
    private val password: String = "pwd"

    private var listener: CustomEventListener? = null


    sealed class UIState : TUIState {
        init {
            println("State changed to ${this}")
        }

        data object Loading : UIState()
        data object UiLoaded : UIState()
        data class Success(val message: String) : UIState()
    }

    inner class Helper(): TUIState {
        val isValidPassword
            get() = username == "luca" && password == "pwd"

    }

    init {
        println(Direction.entries)
        println(printAllValues<Direction>())
    }

    fun addEventListener(listener: CustomEventListener) {
        this.listener = listener
    }

    fun outputOfSideEffect(): Int? {
        return this.listener?.sideEffectCalulated
    }

    fun test1() {
        listener?.onEvent(CustomEvent("test1"))
    }

    fun test2() {
        listener?.onEvent(CustomEvent("test2"))
    }

    private var myPrivateSideEffectCalculated: Int = 0

    fun testCustomEventPass(customEvent: CustomEvent) {
        when (customEvent.eventType) {
            "test1" -> {
                myPrivateSideEffectCalculated += 1
            }
            "test2" -> {
                myPrivateSideEffectCalculated += 2
            }
        }
        listener?.sideEffectCalulated = myPrivateSideEffectCalculated
    }

    var state: UIState = UIState.Loading

    fun printSomethingFunnyWhenCalled() {
        println("You wish you were doing Android development but we are not ready yet")
    }

    fun checkPassword(): Boolean {
        return Helper().isValidPassword
    }

    fun loadUi() {
        state = UIState.UiLoaded
    }

    private var points = 0

    fun moveShip(direction: Direction) {
        when (direction) {
            Direction.NORTH -> {
                points += direction.points
                direction.signal()
                println("Print name=${direction.name} ord=${direction.ordinal}")
            }
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST -> points += direction.points * 2
        }
        println("Ship moving" + points)

        if (direction.isNorthEast) {
            direction.saySomething()
        }
    }

    inline fun <reified T : Enum<T>> printAllValues() {
        println(enumEntries<T>().joinToString { it.name })
    }

    fun plusOperation(a: Int, b: Int): Int {
        return IntArithmetics.PLUS.applyAsInt(a,b)
    }

    fun timesOperation(a: Int, b: Int): Int {
        return IntArithmetics.TIMES.applyAsInt(a,b)
    }
}