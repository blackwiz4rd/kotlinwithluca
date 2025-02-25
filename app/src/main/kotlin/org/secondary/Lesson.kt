package org.secondary

import java.util.*

open class EducationalContent(time: Int) {
    private var name: String? = null

    open var time: Int? = null

    constructor(time: Int, name: String) : this(time) {
        this.name = name
        this.time = time
        println("Running super ${name} ${time}")
    }

    final override fun toString(): String {
        return "name=${name}, time=${time}"
    }

    open fun test() {

    }
}

class Lesson : EducationalContent {
    private var name: String? = null

    override var time: Int? = null

    constructor(time: Int) : super(time)
    constructor(time: Int, name: String) : super(time*100, name.uppercase(Locale.getDefault())) {
        this.name = name
        this.time = time
    }

    override fun test() {
        println("This is a lesson ${name} ${time}")
    }
}