package org.batteryparkdev.kotest.arrow.effect

import arrow.core.Either
import arrow.core.right
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.FunSpec

class EitherTest: FunSpec ({
    test ("it should be right")
  {
        val result: Either<Nothing, Int> = (2 + 2).right()
        result.shouldBeRight(4)
    }
})