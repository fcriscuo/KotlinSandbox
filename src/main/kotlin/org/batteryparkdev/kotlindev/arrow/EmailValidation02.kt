package org.batteryparkdev.kotlindev.arrow

import arrow.core.Validated
import arrow.core.handleError
import arrow.core.invalidNel
import arrow.core.valid

@JvmInline
value class Email2 private constructor(private val value: String) {
    operator fun invoke() = value
    companion object {
        fun valueOf(value: String?): Validated<ValidationErrors, Email2> =
            when {
                value == null -> ValidationError("email should not be null").invalidNel()
                isValidEmail(value) -> Email2(value).valid()
                else -> ValidationError("'$value' should be a valid email address").invalidNel()
            }

        private fun isValidEmail(value: String) =
            value.contains('@')


    }
}

fun main() {
    //it looks like we're calling the constructor,
    //but we are actually calling the 'invoke' operator
    //and get back Validated<ValidationErrors, Email>, not Email
    val email = Email2.valueOf("not an email")
    //val email = Email2.valueOf("tom@abc.com")
    when (email.isValid) {
        false -> email
            .tapInvalid { errors -> errors.forEach(::println) }
            //we can map the errors to a new type and continue the flow
            .mapLeft { errors -> errors.map { it.message } }
            //we handle the errors and that's it, to be used as the last invocation
            //also note that error is now a list of strings, as a result of the previous map
            .handleError { errors -> errors.forEach(::println) }
        true -> email
            //just for debug
            .tap { println(it()) }
            //we extract the value from the inline class
            .map { it() }
            //now we have a Validated<ValidationErrors, String>
            .map { println(it) }
    }
}