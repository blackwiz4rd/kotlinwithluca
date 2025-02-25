package org.secondary

class OnlineCourse constructor(
    // primary constructor
    courseId: Int,
    val courseName: String,
    var instructorName: String,
) : Course() {

    constructor(courseId: Int) : this(courseId, "Default Name", "Default Instructor") {
        
    }

    val upperCaseCourseName = courseName.uppercase()

    val durationInMinutes = 0

    internal var platformNullable: String? = null

    init {
        println("First inti durationInMinutes = ${durationInMinutes}, platformNullable=${platformNullable}")
    }

    //    val durationInSeconds
    //        get() = durationInMinutes*60
    //
    //    init {
    //        println("Second inti durationInSeconds = ${durationInSeconds}, upperCaseCourseName = ${upperCaseCourseName}")
    //    }

    constructor(courseId: Int, courseName: String, instructorName: String, platform: String) : this(
        courseId, courseName, instructorName, platform, platform.uppercase()
    ) {
        println("After the private constructor")
    }

    private constructor(courseId: Int, courseName: String, instructorName: String, platform: String, platformUppercase: String) : this(
        courseId, courseName, instructorName
    ) {
        // this is going to run after the init block
        println("In private constructor")
        // this is going to run after the init block
        println("After init block platformNullable=${platform}, platformUppercase=${platformUppercase}")
        platformNullable = platform
    }

    override fun listStudents() = listOf<String>("Marco", "Mirco", "Giuseppe")

}