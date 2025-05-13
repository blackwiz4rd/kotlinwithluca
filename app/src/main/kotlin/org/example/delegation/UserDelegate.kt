package org.example.delegation

import kotlin.properties.Delegates

class UserDelegate(map: MutableMap<String, Any?>) {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new (${prop.name})")
    }

    // e.g. if you need to do age validation but don't want to rescrict with a check on it
    var age: Int by Delegates.vetoable(0) { prop, old, new ->
        println("Trying to change age from $old to $new")
        new >= 0 // accept only non-negative values
    }

    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: String by this::name

    val address: String by map
    val addressCode: Int     by map
    var city: String by map

    val mutableMap: MutableMap<String, Any?> = map

    init {
        println("User instance created")
    }

    fun isValid(): Boolean {
        println("Validating user $name...")
        return name.isNotBlank()
    }

    fun doSomething() {
        println("User $name is doing something important.")
    }

    companion object {
        fun example(computeUser: () -> UserDelegate, someCondition: Boolean) {
            val memoizedUser by lazy(computeUser)

            if (someCondition && memoizedUser.isValid()) {
                memoizedUser.doSomething()
            } else {
                println("Condition not met or user invalid.")
            }
        }
    }
}