package org.batteryparkdev.kotest

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

/**
 * Created by fcriscuo on 2022Oct05
 */
class AnnotationSpecExample: AnnotationSpec() {
    @BeforeAll
    fun beforeAll() = println("Starting Tests")

    @BeforeEach
    fun beforeEach() = println("Starting a test")

    @Test
    fun test1() {
        2 + 2 shouldBe 4
    }

    @Test
    fun test2() {
        8 % 2 shouldBe 0
    }

    @AfterAll
    fun afterall() = println("FINIS...")
}