package org.batteryparkdev.kotest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldHaveLength

/**
 * Created by fcriscuo on 2022Oct05
 */
class TestCallbacks: FunSpec ({
    beforeEach{
        println("Hello fom $it")
    }

    test("name should be only three letters"){
        "Sam".shouldHaveLength(3)
    }
    test("name should be only six letters"){
        "Howard".shouldHaveLength(6)
    }

    afterEach {
        println ("Goodbye from $it")
    }
})