package org.example.source

// T a covariant type parameter
// useful when you design an interface that needs type T as return type for it's functions
interface Source<out T> {
    fun nextT(): T
    // not allowed
    // fun passMeT(t: T) {}
}

class SourceImpl<T>: Source<T> {
    lateinit var allElements: List<T>
    private var i = 0
    fun initElements(l: List<T>) {
        allElements = l
    }

    override fun nextT(): T {
        val stored = allElements.get(i)
        i += 1
        return stored
    }
}