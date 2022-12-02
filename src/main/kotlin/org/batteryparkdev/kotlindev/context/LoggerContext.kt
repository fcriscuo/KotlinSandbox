package org.batteryparkdev.kotlindev.context

import arrow.core.continuations.Effect
import arrow.core.continuations.EffectScope
import arrow.core.continuations.effect

/**
 * Created by fcriscuo on 2022Oct01
 */
@JvmInline value class Content(val body: List<String>)

sealed interface FileError
@JvmInline value class SecurityError(val msg: String?) : FileError
@JvmInline value class FileNotFound(val path: String) : FileError
object EmptyPath : FileError {
    override fun toString() = "EmptyPath"
}

context(EffectScope<FileError>)
suspend fun readFile(path: String?): Content {
    TODO("All functionality from effect { } available here")
}

fun interface Logger2 {
    suspend fun info(msg: String): Unit
 //   suspend fun warn(msg: String): Unit
  //  suspend fun error(msg: String): Unit
}

context(EffectScope<FileError>, Logger2)
suspend fun allFiles( vararg path: String): List<Content> {
    info("Processing files")
    return path.map { readFile(it) }
}

val res2: Effect<FileError, List<Content>> = with(Logger2(::println)){
    effect { allFiles("path1", "path2") }
}

