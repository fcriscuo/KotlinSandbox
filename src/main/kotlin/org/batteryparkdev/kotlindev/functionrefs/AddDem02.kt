package org.batteryparkdev.kotlindev.functionrefs


fun main() {
    val f: (Int, Int) -> Int = ::add// function reference type
    println(f(10,20))
}