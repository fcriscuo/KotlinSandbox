

package org.batteryparkdev.kotlindev.context

class Logger(val name: String) {
    fun log(s: String): Unit = println("$name: $s")
}

class NotificationSender {
    fun send(s: String): Unit = println("NOTIFY: $s")
}

// add interfaces to represent contexts
interface LoggerContext {
    val logger: Logger
}

interface NotificationContext {
    val notificationSender: NotificationSender
}

// declare that a Logger instance and a NotificationSender instance are required in scop
context(LoggerContext, NotificationContext)
fun store(s: String) {
    logger.log("Stored $s on disk  via ${logger.name}")
    notificationSender.send("Successful storage of $s")
}

fun main() {
    val logger = Logger("Main")
    val notificationSender = NotificationSender()

    val loggerContext = object: LoggerContext{
        override val logger = logger
    }

    val notificationContext = object: NotificationContext {
        override val notificationSender = notificationSender
    }

    with (loggerContext) {
        with (notificationContext){
            store("ABC")
            store("XYZ")
            store("123")
        }
    }
}