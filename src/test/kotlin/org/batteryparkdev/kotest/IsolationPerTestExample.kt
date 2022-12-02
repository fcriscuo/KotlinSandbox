package org.batteryparkdev.kotest

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.WordSpec
import java.util.concurrent.atomic.AtomicInteger

/**
 * A new spec will be created for every test case
 * Created by fcriscuo on 2022Oct05
 */
class IsolationPerTestExample: WordSpec() {
    override  fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest

    val counter = AtomicInteger(0)
     init {
         "a" should {
             println("a=".plus(counter.getAndIncrement()))  // 0
             "b"  {
                 println("b=".plus(counter.getAndIncrement()))  // 1
             }
             "c" {
                 println("c=".plus(counter.getAndIncrement()))  //1
             }
         }
     }
}