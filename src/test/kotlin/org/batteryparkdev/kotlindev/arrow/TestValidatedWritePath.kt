package org.batteryparkdev.kotlindev.arrow

import arrow.core.handleError
import org.batteryparkdev.kotlindev.arrow.io.ValidatedReadFilePath
import org.batteryparkdev.kotlindev.arrow.io.ValidatedWriteFilePath

/**
 * Created by fcriscuo on 2022Sep30
 */
fun main() {
    val validFilePathName: String = "/tmp/validfile.tsv"
    val invalidFilePathName: String = "abc/nosuchfile.txt"
    val validatedWritePath= ValidatedWriteFilePath.valueOf(invalidFilePathName)
    when (validatedWritePath.isValid) {
        false -> validatedWritePath
            .tapInvalid { errors -> errors.forEach(::println) }
            //we can map the errors to a new type and continue the flow
            .mapLeft { errors -> errors.map { it.message } }
            //we handle the errors and that's it, to be used as the last invocation
            //also note that error is now a list of strings, as a result of the previous map
            .handleError { errors -> errors.forEach(::println) }
        true -> validatedWritePath
            //we extract the value from the inline class
            .map { it() }
            //now we have a Validated<ValidationErrors, String>
            .map { println(it) }
    }

}