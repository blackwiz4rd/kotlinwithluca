package org.example.delegation

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Resource(val message: String = "default")

fun resourceDelegate(resource: Resource = Resource()): ReadWriteProperty<Any?, Resource> =
    object : ReadWriteProperty<Any?, Resource> {
        var curValue = resource
        override fun getValue(thisRef: Any?, property: KProperty<*>): Resource = curValue
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Resource) {
            curValue = value
        }
    }

class TestDeletegateStandard {
    companion object {
        val readOnlyResource: Resource by resourceDelegate()  // ReadWriteProperty as val
        var readWriteResource: Resource by resourceDelegate()
    }
}