package org.batteryparkdev.kotlindev.arrow

import arrow.core.nonEmptyListOf
import io.kotest.assertions.arrow.core.shouldBeInvalid
import io.kotest.assertions.arrow.core.shouldBeValid
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.batteryparkdev.kotlindev.arrow.io.ValidatedReadFilePath

/**
 * Created by fcriscuo on 2022Sep30
 */
class ValidatedReadPathSpec: FreeSpec({
    "io.ValidatedReadPath.valueOf should return Valid" - {
        val validReadPath = "/tmp/uniprot.tsv"
        "$validReadPath should be valid"{
            val readPath: ValidatedReadFilePath = ValidatedReadFilePath.valueOf(validReadPath).shouldBeValid()
            readPath.invoke() shouldBe validReadPath
        }
    }

    "io.ValidatedReadPath.valueOf should return Invalid" - {
        val invalidReadPath = "/tmp/abc.txt"
        "$invalidReadPath should be invalid"{
            val errors: ValidationErrors = ValidatedReadFilePath.valueOf(invalidReadPath).shouldBeInvalid()
            errors shouldBe nonEmptyListOf(
                ValidationError("/tmp/abc.txt is not a readable file")
            )
        }
    }
}
) {
}