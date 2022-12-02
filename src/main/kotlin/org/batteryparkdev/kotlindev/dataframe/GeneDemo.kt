package org.batteryparkdev.kotlindev.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.io.read
import org.jetbrains.kotlinx.dataframe.io.readCSV


fun main() {
    val df = DataFrame.read("/Volumes/SSD870/COSMIC_rel97/CMC/cmc_export.tsv")
        .filter { "GENE_NAME"< String>() == "BRCA1" }
    println(df["Mutation_AA"])

  //  println("${df["Gene Symbol"] } symbols: ${df["Synonyms"]}")
    //val synonyms = df.split("Tumour Types(Somatic)").by(",")
}
/*
DataFrame.read("titanic.csv")
    .add("lastName") { "name"<String>().split(",").last() }
    .dropNulls("age")
    .filter {
        "survived"<Boolean>() &&
            "home"<String>().endsWith("NY") &&
            "age"<Int>() in 10..20
    }
 */