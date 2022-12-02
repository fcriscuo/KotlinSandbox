package org.batteryparkdev.kotlindev.inline

inline fun <reified T> printTypeName() {
    println(T::class.simpleName)
}

class Worker
class Manager

fun main() {
    printTypeName<Int>()
    printTypeName<Char>()
    printTypeName<String>()

    val employees: List<Any> = listOf(Worker(), Manager(), Worker())
    val workers: List<Worker> = employees.filterIsInstance<Worker>()  // filterIsInstance is reified
     println("The number of workers is ${workers.size}")
}