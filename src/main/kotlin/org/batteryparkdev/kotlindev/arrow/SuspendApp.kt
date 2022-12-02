package org.batteryparkdev.kotlindev.arrow
import arrow.continuations.SuspendApp
import arrow.core.continuations.nullable
import arrow.fx.coroutines.Resource
import kotlinx.coroutines.*



fun main() = SuspendApp {
    Resource(
        acquire = { println("Creating some resource") },
        release = { _, exitCase ->
            println("ExitCase: $exitCase")
            println("Shutting down will take 10 seconds")
            delay(10_000)
            println("Shutdown finished")
        }
    )
        .use {
            println("Application running with acquired resources.")
            awaitCancellation()
        }
}

class Speaker {
    suspend fun nextTalk(): Talk = TODO()
}

class Talk {
    suspend fun getConference(): Conference = TODO()
}

class Conference {
    suspend fun getCity(): City = TODO()
}

class City

suspend fun nextTalkCity(maybeSpeaker: Speaker?): City? =
    nullable {
        val speaker = maybeSpeaker.bind()
        val talk = speaker.nextTalk().bind()
        val conf = talk.getConference().bind()
        val city = conf.getCity().bind()
        city
    }