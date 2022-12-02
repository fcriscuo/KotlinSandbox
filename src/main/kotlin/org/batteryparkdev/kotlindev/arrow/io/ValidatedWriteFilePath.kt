package org.batteryparkdev.kotlindev.arrow.io

import arrow.core.Either
import arrow.core.Validated
import arrow.core.invalidNel
import arrow.core.valid
import org.apache.commons.io.FilenameUtils
import org.batteryparkdev.kotlindev.arrow.ValidationError
import org.batteryparkdev.kotlindev.arrow.ValidationErrors
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.io.path.isWritable
import kotlin.streams.asSequence

/**
 * Inline class and supporting functions to validate that a specified file path name
 * is valid for the local file system and can be written to
 * n.b. specified file path names must be absolute (e.g. /tmp/xyz.txt not xyz.txt)
 */
@JvmInline
value class ValidatedWriteFilePath private constructor (private val filePathName: String) {
    operator fun invoke() = filePathName
    companion object {
        fun valueOf(value: String?): Validated<ValidationErrors, ValidatedWriteFilePath> =
            when {
                value.isNullOrEmpty() -> ValidationError("Filepath should not be null or empty").invalidNel()
                value.startsWith('/').not() -> ValidationError("$value is not an absolute Path").invalidNel()
                isWritable(value) -> ValidatedWriteFilePath(value).valid()
                else -> ValidationError("${value} is not a writable file").invalidNel()
            }

        private fun isWritable(filePathName: String): Boolean =
            Paths.get(FilenameUtils.getFullPathNoEndSeparator(filePathName)).isWritable()
    }

    fun getPath(): Path = Paths.get(this.invoke())

    fun deleteFile(): Either<Exception, String> {
        return try {
            Files.deleteIfExists(this.getPath())
            Either.Right("$filePathName has been deleted")
        } catch (e: Exception){
            Either.Left(e)
        }
    }
}
