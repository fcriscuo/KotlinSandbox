package org.batteryparkdev.kotlindev

data class Player(val name: String, val team: String)

fun main() {
    val players = listOf(
        Player("Alex", "A"),
        Player("Bob","B" ),
        Player("Cal","A")
    )
    val grouped = players.groupBy { it.team }
    println(grouped)
    // use flatMap to ungroup
    println(grouped.flatMap { it.value })
}