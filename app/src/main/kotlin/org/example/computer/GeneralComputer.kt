package org.example.computer

open class GeneralComputer(
    open val height: Int,
    open val width: Int,
    open val manufacturer: String? = null
) {

    abstract class UtilitiesAbstract {
        abstract fun String.stringCapitalize(): String
        abstract fun capitalize(a: String): String
    }

    companion object Utilities : UtilitiesAbstract() {
        override fun String.stringCapitalize() =
            "${this[0].uppercase()}${if (this.isNotEmpty()) this.substring(1) else ""}"

        override fun capitalize(a: String): String {
            return a.stringCapitalize()
        }
    }

    private val utilities = object : UtilitiesAbstract() {
        override fun String.stringCapitalize() =
            "${this[0].uppercase()}${if (this.isNotEmpty()) this.substring(1) else ""}"

        override fun capitalize(a: String): String {
            return a.stringCapitalize()
        }
    }

    object UtilitiesNotCompanion : UtilitiesAbstract() {
        override fun String.stringCapitalize() =
            "${this[0].uppercase()}${if (this.isNotEmpty()) this.substring(1) else ""}"

        override fun capitalize(a: String): String {
            return a.stringCapitalize()
        }
    }/**/

    val possibleManufacturers: List<String?> = listOf("acer", "asus", "benq", "dell", null, null)
    val possibleManufacturersCameCase: List<String?> = possibleManufacturers.map {
        it?.let { manufacturer ->
            manufacturer.stringCapitalize()
        }
    }
    private var codesIndex = 0
    private fun getCodes(size: Int): IntArray {
        return IntArray(size) {
            codesIndex += 1
            it + 10 + codesIndex
        }
    }

    private val codes: IntArray = getCodes(possibleManufacturers.size)
    private var manufacturersIndex = 0
    val possibleManufacturersCodes: Map<String?, Int> = possibleManufacturers.associateWith {
        val c = codes[manufacturersIndex]
        manufacturersIndex += 1
        c
    }
    private val hasManufacturer
        get() = manufacturer != null

    val builtInManufacturer: String by lazy { // the value is computed only on first access
        println("This code only runs when we are accessing builtInManufacturer")
        // compute the string
        if (hasManufacturer) {
            "yes"
        } else {
            "no"
        }
    }

    private fun printManufacturerLength(obj: Any?): Int? {
        // `obj` is automatically cast to `String` on the right-hand side of `&&`
        if (obj is String && obj.length > 0) {
            return obj.length
        }

        return null
    }

    fun printManufacturers() {
        for (p in possibleManufacturers.withIndex()) {
            println("${p.index} ${p.value}")
        }
    }

    fun getPrintString(computer: String): String {
        val easterEgg = when (manufacturer) {
            "asus" -> " easter egg"
            "acer" -> " asdfasdf"
            else -> ""
        }


        return "My $computer has a height=${height}" +
                " width=${width} " +
                "$manufacturer" +
                " hasManufacturer=${hasManufacturer} " +
                "manufacturerLenght=${
                    printManufacturerLength(
                        manufacturer
                    )
                } $easterEgg"
    }

    override fun toString(): String {
        return getPrintString("generalPC")
    }
}