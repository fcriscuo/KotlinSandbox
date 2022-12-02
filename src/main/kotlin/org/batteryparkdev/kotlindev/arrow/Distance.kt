package org.batteryparkdev.kotlindev.arrow

import org.batteryparkdev.kotlindev.arrow.Distance.Companion.kilometers
import org.batteryparkdev.kotlindev.arrow.Distance.Companion.meters

@JvmInline
value class Distance (val valueAsMeter: Int) {
    init {
        require (valueAsMeter  >= MIN_DISTANCE_VALUE)
    }
    val staticValue get() = MIN_DISTANCE_VALUE

    fun calculateCost(distance: Distance): Int =
        when (distance.valueAsMeter){
            in 0..20 -> 10
            in 20..100 -> 20
            else -> 30
        }

    companion object{
        private const val MIN_DISTANCE_VALUE = 0
        fun Int.kilometers() = Distance(this * 1000)
        fun Int.meters() = Distance(this)
    }
}

fun main() {
   println(100.meters())
    println(100.kilometers())
    println("Cost for ${100.kilometers().valueAsMeter} = ${100.kilometers().calculateCost(100.kilometers())}")
}