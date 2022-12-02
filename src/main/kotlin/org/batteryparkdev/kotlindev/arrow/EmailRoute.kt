@file:Suppress("UNSUPPORTED_FEATURE")
package org.batteryparkdev.kotlindev.arrow

import arrow.core.*
import arrow.core.andThen


data class EmailRoute private constructor(
    val from: Email,
    val to: Email,
    val cc: List<Email> = emptyList(),
    val bcc: List<Email> = emptyList()
) {
    companion object {
        context(AllowedSenders, ReceiveEmailConsents)
        fun validated(
            from: String,
            to: String,
            cc: List<String> = emptyList(),
            bcc: List<String> = emptyList()
        ): Validated<ValidationErrors, EmailRoute> =
            validate(
                Email.valueOf(from),
                Email.valueOf(to),
                cc.traverse { Email.valueOf(it) },
                bcc.traverse { Email.valueOf(it) }
            ) { validFrom, validTo, validCc, validBcc ->
                EmailRoute(validFrom, validTo, validCc, validBcc)
            }.andThen { emailRoute ->
                when {
                    emailRoute.cc.isEmpty() && emailRoute.bcc.isEmpty() ->
                        ValidationError("Both cc and bcc are empty").invalidNel()
                    emailRoute.from !in this@AllowedSenders ->
                        ValidationError("'${emailRoute.from}' is not in the list of allowed senders").invalidNel()
                    emailRoute.to !in this@ReceiveEmailConsents ->
                        ValidationError("'${emailRoute.to}' does not consent receiving emails").invalidNel()
                    else -> emailRoute.valid()
                }
            }
    }
}

fun interface ReceiveEmailConsents {
    operator fun contains(email: Email): Boolean
}

fun interface AllowedSenders {
    operator fun contains(email: Email): Boolean
}

fun main() {

}