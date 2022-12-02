package org.batteryparkdev.kotest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldHaveLength

/**
 * Created by fcriscuo on 2022Oct05
 */
class DynamicTests: FunSpec({
    // include invalid name: Mary
    listOf ("Sam", "Mary", "Bob").forEach{
        test("it should be a 3 letter name") {
            it shouldHaveLength (3)
        }
    }
})