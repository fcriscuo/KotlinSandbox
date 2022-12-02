package org.batteryparkdev.kotest

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.system.NoSystemOutListener

/**
 * test will fail trying to write to system out
 * Created by fcriscuo on 2022Oct05
 */
class SystemOutListener: DescribeSpec ({
    listener(NoSystemOutListener)

    describe("All these test cases should not write to standard out"){
        it("silence"){
            println("boom")
        }
    }
})