package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate

public class ProjectConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var group: String by PropertyDelegate(parent)

    public var version: String by PropertyDelegate(parent)

    public var description: String by PropertyDelegate(parent)

    public fun apply(init: ApplyDsl.() -> Unit) {
        block("apply") {
            ApplyDsl(this).apply(init)
        }
    }

    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    public fun dependencies(init: DependencyHandlerDsl.() -> Unit) {
        block("dependencies") {
            DependencyHandlerDsl(this).apply(init)
        }
    }

    public fun property(name: String, value: String) {
        line("project.extra[${formatValue(name)}] = $value")
    }
}

public class ApplyDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun plugin(id: String) {
        line("plugin(${formatValue(id)})")
    }

}