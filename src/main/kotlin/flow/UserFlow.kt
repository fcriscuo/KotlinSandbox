package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


fun usersFlow(): Flow<String> = flow{
    repeat(3){
        delay(1000)
        val ctx = currentCoroutineContext()
        val name = ctx[CoroutineName]?.name
        emit("User$it in $name")
    }
}

suspend fun main() {
    val users = usersFlow()

    withContext(CoroutineName("Name")) {
        val job = launch{
            //collect is suspending
            users.collect{println(it)}
        }
        launch{
            delay(2100)
            println("Got enough")
            job.cancel()
        }
    }

}