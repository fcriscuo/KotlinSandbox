package org.batteryparkdev.kotlindev.functionrefs

data class ComplexNumber(val real: Double, val imaginary: Double ) {
    fun doubled(): ComplexNumber =
        ComplexNumber(this.real *2, this.imaginary * 2)
    fun times(num: Int): ComplexNumber =
        ComplexNumber(real*num, imaginary * num)
}

fun main() {
    val c1 = ComplexNumber(1.0, 2.0)

    val f1: (ComplexNumber) -> ComplexNumber = ComplexNumber::doubled
    println(f1(c1))

    val f2: (ComplexNumber, Int) -> ComplexNumber = ComplexNumber::times
    println(f2(c1,4))
}