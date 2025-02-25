package org.secondary

import org.example.address.Address
import org.example.address.TestAddress
import org.example.miscellaneous.Customer as MiscellaneousCustomer
import org.secondary.Customer

fun playWithFunctionalInterfaces() {
    val isEven = object : IntPredicate {
        override fun accept(i: Int): Boolean {
            return i % 2 == 0
        }

        override fun printing(i: Int): String {
            return super.printing(i)
        }
    }

    // SAM (Single Abstract Method) conversion only works with interfaces that have a single abstract method
    // SAM cannot override printing
    val isEvenSAM = IntPredicate { i -> i % 2 == 0 }

    // typealiases don't create a new type and cannot override printing
    val isEventTypeAlias: IntPredicateAlias = { i -> i % 2 == 0 }
    val isOddTypeAlias: IntPredicateAlias = { i -> i % 2 != 0 }

    println("isEven number: ${isEven.accept(4)}, ${isEven.printing(4)}")
    println("isEven number: ${isEvenSAM.accept(4)}")
    println("isEven number: ${isEventTypeAlias(4)}")
    println("isOdd number: ${isOddTypeAlias(4)}")
}

fun playWithClasses() {
    println(MiscellaneousCustomer("name","email","surname"))
    println(Customer("nameSecondary"))
    printCustomer()

    val onlineCourse = OnlineCourse(1, "Kotlin Course", "Luca")
    // argument that we are passing is not declared as a value or variable
    // print("courseId=${onlineCourse.courseId}")
    println("courseName=${onlineCourse.courseName}")
    println("instructorName=${onlineCourse.instructorName}")
    onlineCourse.instructorName = "Ettore"
    println("changed instructorName=${onlineCourse.instructorName}")

    val onlineCourse2 = OnlineCourse(
        2,
        "Flutter Course",
        "Luca",
        "YouTube"
    )

    println("platformNullable=${onlineCourse2.platformNullable}")

    val anyCourse = Course()
    println("List of students (anyCourse) = ${anyCourse.listStudents()}")

    println("List of students (onlineCourse) = ${onlineCourse.listStudents()}")

}

fun playWithClassInheritance() {
    val l = Lesson(
        time = 1,
        name = "Kotlin Lesson 11"
    )
    println(l)
    l.test()

    println(Empty("Test"))

    println(Customer("Luca").customPhraseName())

    FilledRectangle(3,4).draw()

    Square(5).draw()
}

fun playWithProperties() {
    val address1 = Address(
        name = "John Doe",
        street = "123 Main St",
        city = "New York",
        state = "NY",
        zip = "10001"
    )

    val address2 = Address(
        name = "Jane Smith",
        street = "456 Elm St",
        city = "Los Angeles",
        state = "CA",
        zip = "90012"
    )

    val address3 = Address(
        name = "Acme Corp",
        street = "789 Oak Ave",
        city = "Chicago",
        state = "IL",
        zip = "60601"
    )

    println("${address1}\n${address2}\n${address3}")
    address3.city = address1.city
    println("\n")
    println("${address1}\n${address2}\n${address3}")

    val rectangle = Rectangle(3,5)
    println("${rectangle} with area ${rectangle.area} ${rectangle.calculateArea()} ${rectangle.areaBad}")

    rectangle.width = 10
    println("${rectangle} with area ${rectangle.area} ${rectangle.calculateArea()} ${rectangle.areaBad}")

    println(rectangle.textInside)

    rectangle.newTextInside("Hello")
    println(rectangle.textInside)

    val testAddress = TestAddress()
    testAddress.setup()
    testAddress.test()
}