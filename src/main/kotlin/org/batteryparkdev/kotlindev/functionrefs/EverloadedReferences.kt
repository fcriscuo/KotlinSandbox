package org.batteryparkdev.kotlindev.functionrefs

// overloaded functions
fun foo(i: Int) = 1
fun foo(str: String) = "AAA"


fun main() {

    // specify the type for overloaded functions
    val fooInt: (Int)->Int = ::foo
    println(fooInt(123))  // 1
    val fooString: (String) -> String = ::foo
    println(fooString("")) // AAA

    // overloaded constructors from ConstructorRefs.kt
    val intToUserId: (Int) -> UserId = ::UserId
    println(intToUserId(1).value)

    val studentId= StudentId(2)
    val studentIdToUserid2: (StudentId) -> UserId = :: UserId
    println(studentIdToUserid2(studentId).value)

}