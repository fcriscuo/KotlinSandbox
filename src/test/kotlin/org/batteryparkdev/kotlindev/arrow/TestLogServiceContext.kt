package org.batteryparkdev.kotlindev.arrow

import org.batteryparkdev.kotlindev.arrow.service.LoggerContext
import org.batteryparkdev.kotlindev.arrow.service.loggerContext

/**
 * Created by fcriscuo on 2022Oct01
 */
fun executeStore(s: String) {
    with(loggerContext) {
        store(s)
    }
}
fun executeTests() {
        executeStore("ABC")
        executeStore("123")
    }



context (LoggerContext)
fun store(s: String) {
    logger.info("Stored $s on disk ")
}

fun main() {
   executeTests()
}