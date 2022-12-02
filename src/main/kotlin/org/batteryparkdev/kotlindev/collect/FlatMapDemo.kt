package org.batteryparkdev.kotlindev.collect

fun main() {
    val names = listOf<String>("Ann","Bob","Charles" )
    val chars1: List<Char> = names.flatMap { it.toList() }
    println(chars1)
    val mapRes: List<List<Char>> = names.map { it.toList()}
    println(mapRes)
    val chars2 = mapRes.flatten()
    println(chars2)
    println(chars1 == chars2)
}
