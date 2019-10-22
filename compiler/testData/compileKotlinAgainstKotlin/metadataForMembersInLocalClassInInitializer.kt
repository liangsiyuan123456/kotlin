// TARGET_BACKEND: JVM
// IGNORE_BACKEND: JVM_IR
// FILE: A.kt

// This test fails on JVM_IR currently because InitializersLowering invokes deep copy on contents of the properties o and k to copy them
// to the class initializer, and deep copy does not perform copy for metadataSource of functions and properties. As a consequence,
// JVM signature, which depends on JvmName, is not written for getO and k, and kotlin-reflect tries to call incorrect members.

class A {
    val o = object {
        @JvmName("jvmGetO")
        fun getO(): String = "O"
    }

    val k = object {
        @get:JvmName("jvmGetK")
        val k: String = "K"
    }
}

// FILE: B.kt

import kotlin.reflect.full.*

fun box(): String {
    val a = A()
    val obj1 = a.o
    val o = obj1::class.declaredMemberFunctions.single().call(obj1) as String
    val obj2 = a.k
    val k = obj2::class.declaredMemberProperties.single().call(obj2) as String
    return o + k
}
