package org.batteryparkdev.kotlindev.koindev

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.core.logger.PrintLogger

class HelloApplication: KoinComponent {
    val helloService by inject<HelloService>()
    fun sayHello() = println(helloService.hello())

}
val helloModule = module {

    singleOf(::HelloMessageData)

    singleOf(::HelloServiceImpl) { bind<HelloService>() }
}

fun main(vararg ars: String) {
    startKoin {
        modules(helloModule)
        printLogger(Level.INFO)
    }
    HelloApplication().sayHello()

}