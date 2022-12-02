package org.batteryparkdev.kotlindev.arrow.service

import mu.KotlinLogging

/*
Responsible for providing a consistent logging structure for all components
 */

interface LogService {
    fun info(message: String)
    fun fine(message: String)
    fun warn(message: String)
    fun error(message: String)
    fun exception(e:Exception)
}

class LogServiceImpl(): LogService {
    private val logger = KotlinLogging.logger {}
    override fun info(message: String) = logger.info { message }
    override fun fine(message: String) = logger.debug { message }
    override fun warn(message: String) = logger.warn { message }
    override fun error(message: String) = logger.error{message}
    override fun exception(e:Exception) = logger.error{e.message}
}

interface LoggerContext {
    val logger: LogService
}
val loggerContext = object: LoggerContext{
    override val logger = LogServiceImpl()
}

fun Exception.log() = LogServiceImpl().exception(this)

fun main() {
    LogServiceImpl().warn("This is a test of the kotlin logging configuration")
}