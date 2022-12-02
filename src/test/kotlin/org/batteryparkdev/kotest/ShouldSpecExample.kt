package org.batteryparkdev.kotest

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

/**
 * Created by fcriscuo on 2022Oct05
 */
class ShouldSpecExample:  ShouldSpec( {
    context("String.length") {
        should("return the length of a string") {
            "sammy".length shouldBe 5
            "".length shouldBe 0
        }
    }
})