package org.batteryparkdev.kotlindev.functionrefs

class StudentId( val value:Int)
class UserId(val value: Int) {
    constructor(studentId: StudentId) : this(studentId.value)
}

fun main() {
    val ints: List<Int> = listOf(1,2,2,3,5,8)
    val studentIds: List<StudentId> = ints.map (::StudentId )
    val userIds: List<UserId> = studentIds.map(::UserId)

}