package org.batteryparkdev.kotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith

/**
 * Created by fcriscuo on 2022Oct05
 */
class MyTests: StringSpec ({
    "length should return size of String" {
        "hello".length shouldBe 5
    }
    "startsWith should test for prefix" {
        "world" should startWith("wor")
    }

})