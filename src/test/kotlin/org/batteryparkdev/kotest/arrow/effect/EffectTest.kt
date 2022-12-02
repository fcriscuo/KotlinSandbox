package org.batteryparkdev.kotest.arrow.effect

import arrow.core.Ior
import arrow.core.None
import arrow.core.continuations.Effect
import arrow.core.continuations.effect
import arrow.core.continuations.ensureNotNull
import io.kotest.assertions.arrow.core.shouldBeInvalid
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.matchers.shouldBe
import java.io.File
import java.io.FileNotFoundException

/**
 * Created by fcriscuo on 2022Oct05
 */


@JvmInline
value class Content(val body: List<String>)

sealed interface FileError
@JvmInline value class SecurityError(val msg: String?): FileError
@JvmInline value class FileNotFound (val path: String): FileError
object EmptyPath: FileError {
    override fun toString() = "Specified Path is invalid"
}

fun readFile(path: String?): Effect<FileError, Content> = effect {
    ensureNotNull(path) { EmptyPath }
    ensure(path.isNotEmpty()) { EmptyPath }
    try {
        val lines = File(path).readLines()
        println("lines read = ${lines.size}")
        Content(lines)
    } catch(e: FileNotFoundException) {
        shift(FileNotFound(path))
    } catch (e: SecurityException) {
        shift(SecurityError(e.message))
    }
}

suspend fun main() {
    println(readFile("").toEither().shouldBeLeft())
    readFile("knit.properties").toValidated().shouldBeInvalid(FileNotFound("knit.properties"))
    readFile("gradle.properties").toIor() shouldBe Ior.Left(FileNotFound("gradle.properties"))
    readFile("README.MD").toOption { None } shouldBe None
    val contentList = readFile("./data/P05067.xml").toValidated().toList()
    if (contentList.isNotEmpty()) {
        contentList[0].body.asSequence().forEach { println(it) }
    } else {
        println("File is invalid")
    }
}

