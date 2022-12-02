package org.batteryparkdev.kotlindev.anonymous

import kotlin.random.Random

// regular function
fun add1(a: Int, b: Int) = a+b
// anonymous version
val add2 = fun (a:Int, b: Int):Int {
    return a+b
}

val sub: (Int, Int) -> Int = fun(a:Int, b: Int): Int {
    return a-b
}

fun operation (x: (Int,Int) ->Int,a: Int, b: Int) = x.invoke(a,b)

val printNumber: (Int) -> Unit = fun(i: Int) { println(i)}

 fun generateUniqueString(
    length: Int,
    seed: Long = System.currentTimeMillis()
): Sequence<String> = sequence {
    val random = Random(seed)
    val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    while (true) {
     //for (i in 1..20) {
        val randomString = (1..length)
            .map { i -> random.nextInt(charPool.size) }
            .map(charPool::get)
            .joinToString("");
        yield(randomString)
    }
}.distinct()


fun main() {
    printNumber(add2(10,20))
    println(operation(sub, 20,4))
    println("ABC"::class.simpleName)
    val seq = generateUniqueString(4).iterator()
    for ( i in 1..2000)run {
       println(seq.next())
    }
    val z = "\"125\""
    println(z)
    println(z.removeSurrounding("\"").toIntOrNull())
    val y = "126"
    println("$y  ${y.removeSurrounding("\"").toIntOrNull()} ")

    val x = "\"\"890\"\""
    println(x)
    println(x.removeSurrounding("\""))


}