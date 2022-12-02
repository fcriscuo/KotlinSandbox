package org.batteryparkdev.kotlindev.arrow.effect

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.apache.commons.csv.CSVRecord

/**
 * Created by fcriscuo on 2022Oct06
 */
class TestSequenceEffect {
    private var nodecount: Int = 0


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun CoroutineScope.produceCSVRecords(filename: String, dropCount: Int = 0) =
        produce<CSVRecord> {
            val sequence = generateFileSequence(filename).toValidated().toOption().orNull()?.body
            sequence?.drop(dropCount)?.filter { it.size() >0 }?.forEach {
                send(it)
                delay(20)
            }
        }
    fun processDataFile(filename: String, dropCount: Int = 0) =  runBlocking {
          val records = produceCSVRecords(filename, dropCount)
          for ( record in records) {
              nodecount += 1
              println("Record: ${record[0]}")
          }
        println("Record count = $nodecount")
    }
}
fun main() {
    val start = System.currentTimeMillis()
    TestSequenceEffect().processDataFile("./data/classification.csv", 0)
    println("Elapsed time: = ${(System.currentTimeMillis() - start) / 1000} seconds")
}