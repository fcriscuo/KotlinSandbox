package org.batteryparkdev.kotest

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.WordSpec

/**
 *  a new spec will be created for every leaf test case
 * Created by fcriscuo on 2022Oct05
 */
class IsolationPerLeafExample: WordSpec() {

    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

    init {
        "a" should {
            println("Hello")
            "b" {
                println("From")
            }
            "c" {
                println("Sam")
            }
        }
    }
}