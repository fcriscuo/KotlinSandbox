package org.batteryparkdev.kotlindev.sequence

fun m (i: Int): Int {
    print("m$i ")
    return i * i
}

fun f (i: Int):Boolean {
    print (" f$i ")
    return i >= 10
}

fun main() {
    println("List")
    listOf<Int>(1,2,3,4,5,6,7,8,9,10)
        .map { m(it) }
        .find{ f(it)} // when true, operation is terminal
        .let { print(it) }
    println("\nSequence\n")

    sequenceOf(1,2,3,4,5,6,7,8,9,10)
        .map { m(it) }
        .find{ f(it)}  // operation is terminal
        .let{print(it)}
}