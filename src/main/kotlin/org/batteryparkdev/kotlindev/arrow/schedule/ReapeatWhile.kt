package org.batteryparkdev.kotlindev.arrow.schedule

import arrow.fx.coroutines.Schedule

/**
 * Created by fcriscuo on 2022Oct06
 */
suspend fun main() {
    var counter = 0
    // start
    val res = Schedule.doWhile<Int> {it <= 5}.repeat {
        println("Run ${counter++}"); counter
    }
    // end
    println(res)
}