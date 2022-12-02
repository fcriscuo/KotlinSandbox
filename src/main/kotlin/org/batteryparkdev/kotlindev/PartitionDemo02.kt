package org.batteryparkdev.kotlindev

fun main() {
    val nums = (1..10).toList()
    val (smaller, bigger) = nums.partition { it <= 5 }
    println(smaller)
    println(bigger)
    val (even,odd) = nums.partition { it % 2 == 0 }
    println(even)
    println(odd)

    data class Student (val name: String, val passing: Boolean)
    val students = listOf(Student(name="Alex", true),
    Student(name="Tom", false))
    val (passing, failed) = students.partition { it.passing }
    println(passing)
    println(failed)


}