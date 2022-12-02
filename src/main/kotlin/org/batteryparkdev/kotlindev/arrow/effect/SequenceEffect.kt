package org.batteryparkdev.kotlindev.arrow.effect

import arrow.core.continuations.Effect
import arrow.core.continuations.effect
import arrow.core.continuations.ensureNotNull
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by fcriscuo on 2022Oct05
 */
@JvmInline
value class CsvRecordSequence(val body: Sequence<CSVRecord>)

fun generateFileSequence(path: String?): Effect<FileError, CsvRecordSequence> = effect {
    ensureNotNull(path) { EmptyPath}
    ensure(path.isNotEmpty()) {EmptyPath}
    ensure(File(path).exists()) {EmptyPath}
    try{
        val reader = Files.newBufferedReader(Paths.get(path))
        val parser = CSVParser.parse(
            reader,
            generateCSVFormat(path)
        )
        CsvRecordSequence(parser.records.asSequence())
    }catch(e: FileNotFoundException) {
        shift(FileNotFound(path))
    } catch (e: SecurityException) {
        shift(SecurityError(e.message))
    }
}
//TODO: refactor deprecated operations
private fun generateCSVFormat(path:String):CSVFormat =
    when (path.endsWith("tsv")) {
        true -> CSVFormat.TDF.withFirstRecordAsHeader().withQuote(null).withIgnoreEmptyLines()
        false -> CSVFormat.RFC4180.withHeader().withIgnoreEmptyLines().withQuote(null)
    }

suspend fun main() {
    val sequenceContainer = generateFileSequence("./data/classification.csv").toValidated().toOption()
    if (sequenceContainer.isNotEmpty()){
        sequenceContainer.orNull()?.body?.forEach {record -> println(record.get(0))  }
    } else {
        println("Unable to parse file")
    }
}


