class TestSealed {
    public fun testSealedFromOtherPackage() {
        val error: CustomError = CustomError.DatabaseError()

        println(error)
    }
}