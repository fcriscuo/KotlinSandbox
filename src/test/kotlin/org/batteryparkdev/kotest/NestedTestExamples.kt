package org.batteryparkdev.kotest

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

/**
 * Created by fcriscuo on 2022Oct05
 */
class NestedTestExamples: DescribeSpec( {
    describe ("an outer test")
    it("an inner test") {
        2 + 2 shouldBe 4
    }
    it ("another inner test"){
        3 * 3 shouldBe  9
    }
})