package org.batteryparkdev.kotlindev
import kotlinx.coroutines.*

fun main() {
    val names = listOf("Marcin", "Maja", "Cookie")
    val byCapital = names.groupBy { it.first() }  // group by first letter of name
    println(byCapital)
    val byLength =names.groupBy { it.length }
    println(byLength)
}
