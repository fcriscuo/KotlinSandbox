package org.batteryparkdev.kotlindev.functionrefs

class Drone {
    fun takeOff() {}
    fun land() {}
    companion object{
        fun makeDrone(): Drone = Drone()
    }

}

fun main() {
    val maker: () -> Drone = Drone.Companion::makeDrone
}