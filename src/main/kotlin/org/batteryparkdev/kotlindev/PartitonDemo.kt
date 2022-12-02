package org.batteryparkdev.kotlindev
fun main() {
    val nums = listOf(1,2,6,11)
    val partitioned:Pair<List<Int>,List<Int>> =
        nums.partition { it in 2..10 }
    println(partitioned)
    val (inRange, notInRange) = partitioned
    println(inRange)
    println(notInRange)
}