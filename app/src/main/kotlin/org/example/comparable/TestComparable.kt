import kotlin.reflect.typeOf

interface TestComparable<in T> {
    operator fun compareTo(other: T): Int
}

class TestComparableImpl<T>(private val first: T): TestComparable<T> {
    override fun compareTo(other: T): Int {
        if (first is Int && other is Int) {
            return if ((first as Int) > (other as Int)) 1 else 0
        }
        return 0
    }

    override fun toString(): String = "$first"
}