package org.example.username

interface FactoryCrud<T> {
    fun create(name: String): T
}

class User(val name: String) {
    // Defines a companion object that acts as a factory for creating User instances
    companion object Factory: FactoryCrud<User> {
        private val onlyInClassMember = "hello"
        private fun onlyInClassFunction() {
            println("Hello world, I'm private to the object!")
        }
        override fun create(name: String): User = User(name)
    }

    fun exposeOnlyInClassMember() {
        println(onlyInClassMember)
        println(onlyInClassFunction())
    }
}

fun testUserCreation(){
    // Calls the companion object's factory method using the class name as the qualifier.
    // Creates a new User instance
    val userInstance: FactoryCrud<User> = User
    val user = userInstance.create("John Doe")
    println(user.name)
    // John Doe
    user.exposeOnlyInClassMember()
}