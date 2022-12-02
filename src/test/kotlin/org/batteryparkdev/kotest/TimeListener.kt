package org.batteryparkdev.kotest

import io.kotest.core.listeners.AfterTestListener
import io.kotest.core.listeners.BeforeTestListener
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import junit.framework.TestCase
import junit.framework.TestResult

/**
 * Created by fcriscuo on 2022Oct05
 */
object TimerListener: BeforeTestListener, AfterTestListener{

    var started = 0L
    fun beforeTest(testCase: TestCase): Unit {
        started = System.currentTimeMillis()
    }
     fun afterTest(testCase: TestCase, result: TestResult): Unit {
        println("Duration of ${testCase.name} = " + (System.currentTimeMillis() - started))
    }
}

class MyTestClass : FunSpec({
    extensions(TimerListener)
    test("my first test"){
        1 + 2 shouldBe 3
    }
})