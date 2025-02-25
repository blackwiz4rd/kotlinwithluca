package org.secondary

// visible in the same module = internal
// visibile in this file = private
// not available for top level declarations = protected
internal fun createEv() {
    val electricCar = ElectricCar("Tesla", "Model S", 2023, 100)
    electricCar.startEngine()
    println("car's ${electricCar.yearString}")
}
open class Vehicle(val brand: String, val model: String, val year: Int) {

    protected fun getBrandAndModel(): String = "$brand $model"
    internal val yearString = "year=${year}"

    open fun startEngine() {
        println("The engine of the ${getBrandAndModel()} is starting ${yearString}...")
    }
}

class ElectricCar(brand: String, model: String, year: Int, val batteryCapacity: Int) : Vehicle(brand, model, year) {

    override fun startEngine() {
        println("The electric engine of the ${getBrandAndModel()} is silently starting with $batteryCapacity kWh battery (${yearString}).")
    }
}