package org.secondary

open class Course : AbstractCourse() {
    override fun listStudents() = listOf<String>("Marco", "Mirco")
}
