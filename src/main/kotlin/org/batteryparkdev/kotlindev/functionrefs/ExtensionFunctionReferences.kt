package org.batteryparkdev.kotlindev.functionrefs

data class Number2(val num: Int)

fun Number2.toFloat(): Float = num.toFloat()
fun Number2.times (n: Int): Number2 = Number2(num * n)

fun main() {
    val num = Number2(10)
    // extension function reference
    val float: (Number2) -> Float = Number2::toFloat
    println(float(num))
    val multiply: (Number2, Int) -> Number2 = Number2::times
    println(multiply(num, 4))
}