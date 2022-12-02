package org.batteryparkdev.kotlindev.arrow.io

import arrow.core.Either
import arrow.core.Validated
import arrow.core.invalidNel
import arrow.core.valid
import org.batteryparkdev.kotlindev.arrow.ValidationError
import org.batteryparkdev.kotlindev.arrow.ValidationErrors
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence

/**
 * Inline class and supporting functions to validate that a specified file path name
 * is valid for the local file system and is readable
 * n.b. specified file path names must be absolute (e.g. /tmp/xyz.txt not xyz.txt)
 * Imported from legacy project by fcriscuo on 2022Jul28
 *
 */
@JvmInline
value class ValidatedReadFilePath private constructor (private val filePathName: String) {
    operator fun invoke() = filePathName
    companion object {
        fun valueOf(value: String?): Validated<ValidationErrors, ValidatedReadFilePath> =
            when {
                value.isNullOrEmpty() -> ValidationError("Filepath should not be null or empty").invalidNel()
                value.startsWith('/').not() -> ValidationError("$value is not an absolute Path").invalidNel()
                isReadable(value) -> ValidatedReadFilePath(value).valid()
                else -> ValidationError("${value} is not a readable file").invalidNel()
            }

        private fun isReadable(filePathName: String): Boolean =
            File(filePathName).exists().and(File(filePathName).canRead())
    }

    fun readFileAsStream(): Stream<String> = Files.lines(this.getPath())

    fun readFileAsSequence(): Sequence<String> =readFileAsStream().asSequence()

    fun getPath(): Path = Paths.get(filePathName)


    fun deleteFile(): Either<Exception, String> {
        return try {
            Files.deleteIfExists(this.getPath())
            Either.Right("$filePathName has been deleted")
        } catch (e: Exception){
            Either.Left(e)
        }
    }
}
