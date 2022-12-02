package org.batteryparkdev.kotest

import io.kotest.core.annotation.EnabledCondition
import io.kotest.core.annotation.EnabledIf
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.testcontainers.shaded.org.apache.commons.lang3.SystemUtils.IS_OS_LINUX
import kotlin.reflect.KClass

/**
 * Created by fcriscuo on 2022Oct05
 * Only run tests that whose name contains Linux on a Linux system
 */
class LinuxOnlyCondition: EnabledCondition {
    override fun enabled(kclass: KClass<out Spec>): Boolean = when {
        kclass.simpleName?.contains("Linux") == true -> IS_OS_LINUX
        else -> true
    }
}

    @EnabledIf(LinuxOnlyCondition::class)
    class MyLinuxTest1: FunSpec( {
        test("my first test"){
            1 + 2 shouldBe 3
        }
    })
