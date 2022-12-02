package org.batteryparkdev.kotest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

/**
 * Created by fcriscuo on 2022Oct05
 */
class MyFirstTestClass: FunSpec( {
    test("my first test"){
        1 + 2 shouldBe 3
    }
})