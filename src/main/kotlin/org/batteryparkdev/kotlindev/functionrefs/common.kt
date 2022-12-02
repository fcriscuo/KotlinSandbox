package org.batteryparkdev.kotlindev.functionrefs

data class Complex(val real: Double, val imaginary: Double) {

    fun doubled(): Complex =
        Complex(this.real * 2, this.imaginary * 2)

    fun times(num: Int) =
        Complex(real * num, imaginary * num)
}
    fun zeroComplex(): Complex = Complex(0.0, 0.0)

    fun makeComplex(
        real: Double = 0.0,
        imaginary: Double = 0.0
    ) = Complex(real, imaginary)

    fun Complex.plus(other: Complex): Complex =
        Complex(real + other.real, imaginary + other.imaginary)
    fun Int.toComplex() = Complex(this.toDouble(), 0.0)


fun main() {
    val f1: () -> Complex = ::zeroComplex
    println(f1())
    val f2:(Double,Double) -> Complex = ::makeComplex
    println(f2(1.0, 2.0))
}
