package org.batteryparkdev.kotlindev

inline fun <R> run(block:() -> R ): R = block()

inline fun repeat(times: Int, block: (Int) -> Unit) {
    for (i in 0 until times) {
        block(i)
    }
}

fun sum(a: Int, b: Int) = (a..b).fold(0) {acc,i -> acc+i}
fun product (a:Int, b: Int) = (a..b).fold(1) {acc, i -> acc * i}


fun main() {
    run ({println("A")})
    run() {println("A")}
    run { println("A")}

    repeat(2, {print("B")})
    println()
    repeat(2){ print("b")}
    println()


    val printTimes = {text:String, times: Int ->
        for (i in 1..times){
            print(text)
        }
    }  // the type i s (text: String, times: Int) -> Unit
    printTimes("Na", 7)
}