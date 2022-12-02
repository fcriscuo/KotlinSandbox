package org.batteryparkdev.kotlindev.koindev

interface HelloService {
    fun hello(): String
}
class HelloServiceImpl( private val helloMessageData: HelloMessageData) : HelloService {
    override fun hello()  = "Hey, ${helloMessageData.message}"
}
