package org.batteryparkdev.kotlindev.functionrefs

data class Number(val num: Int) {
    fun toFloat(): Float = num.toFloat()
    fun times(n: Int): Number = Number(num * n)
}

fun main() {
    val numberObject = Number(10)
    // `toFloat` has no parameters, but its function type
    // needs a receiver of type `Number`
    val float: (Number) -> Float = Number::toFloat
    println(float(numberObject))
    // `times` has one parameter of type `Int`, but its
    // function type also needs a receiver of type `Number`
    val multiply: (Number, Int) -> Number = Number::times
    println(multiply(numberObject, 4))
}