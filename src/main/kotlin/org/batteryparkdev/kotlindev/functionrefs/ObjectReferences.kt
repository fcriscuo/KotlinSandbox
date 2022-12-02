package org.batteryparkdev.kotlindev.functionrefs

object Robot {
    fun moveForward() {}
    fun moveBackward() {}
}

fun main() {
    Robot.moveBackward()
    Robot.moveForward()

    val action1: ()-> Unit = Robot::moveBackward
    val action2: ()-> Unit = Robot::moveForward

    action1()
}