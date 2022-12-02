package org.batteryparkdev.kotest

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

/**
 * Project wide settings
 * Created by fcriscuo on 2022Oct05
 */
class ProjectConfig: AbstractProjectConfig() {
    override val isolationMode = IsolationMode.InstancePerLeaf
}