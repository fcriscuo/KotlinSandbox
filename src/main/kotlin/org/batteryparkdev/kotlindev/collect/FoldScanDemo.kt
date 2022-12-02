package org.batteryparkdev.kotlindev.collect

fun main() {
    val numbers = listOf(1,2,3,4)
    println(numbers.fold(0){acc,i -> acc+i}) // 10
    println(numbers.scan(0){acc,i-> acc + i})
    println(numbers.runningFold(0){acc,i -> acc+i})

    println(numbers.fold("") { acc, i -> acc + i }) // 1234
    println(numbers.scan("") { acc, i -> acc + i })
    // [, 1, 12, 123, 1234]
    println(numbers.runningFold("") { acc, i -> acc + i })
    // [, 1, 12, 123, 1234]

    println(numbers.fold(1) { acc, i -> acc * i }) // 24
    println(numbers.scan(1) { acc, i -> acc * i })
    // [1, 1, 2, 6, 24]
    println(numbers.runningFold(1) { acc, i -> acc * i })
    // [1, 1, 2, 6, 24]”

}