package org.batteryparkdev.kotlindev.functionrefs

fun add(a: Int, b: Int) = a+b
fun main() {
    val f = ::add // function reference
    println(f.isOpen)  // false
    println(f.visibility) // PUBLIC
}