package org.batteryparkdev.kotlindev.arrow

import arrow.core.handleError
import org.batteryparkdev.kotlindev.arrow.io.ValidatedReadFilePath
import org.batteryparkdev.kotlindev.arrow.service.LogServiceImpl
import org.batteryparkdev.kotlindev.arrow.service.LoggerContext

/**
 * Created by fcriscuo on 2022Sep30
 */
context(LoggerContext)
fun writeToLog(message: String):Unit {
    logger.info(message)
}
fun main() {
    val loggingContext = object: LoggerContext{
        override val logger: LogServiceImpl
            get() = LogServiceImpl()

    }
    val validFilePathName: String = "/tmp/uniprot.tsv"
    val invalidFilePathName: String = "abc/nosuchfile.txt"
    val validatedReadPath= ValidatedReadFilePath.valueOf(validFilePathName)

    when (validatedReadPath.isValid) {
        false -> validatedReadPath
            .tapInvalid { errors -> errors.forEach(::println) }
            //we can map the errors to a new type and continue the flow
            .mapLeft { errors -> errors.map { it.message } }
            //we handle the errors and that's it, to be used as the last invocation
            //also note that error is now a list of strings, as a result of the previous map
            .handleError { errors -> errors.forEach(::println) }
        true -> validatedReadPath
            //we extract the value from the inline class
            .map { it() }
            //now we have a Validated<ValidationErrors, String>
            .map { println(it) }
    }
    if (validatedReadPath.isValid){
        validatedReadPath.map { it.readFileAsSequence().take(20).forEach { line -> println(line) }}

    }

}