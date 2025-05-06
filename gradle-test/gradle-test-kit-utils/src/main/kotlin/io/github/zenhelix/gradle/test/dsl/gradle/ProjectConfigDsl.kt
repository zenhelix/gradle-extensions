package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.PropertyDelegate

/**
 * DSL for project configuration
 */
public class ProjectConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the project group
     */
    public var group: String by PropertyDelegate(parent)

    /**
     * Sets the project version
     */
    public var version: String by PropertyDelegate(parent)

    /**
     * Sets the project description
     */
    public var description: String by PropertyDelegate(parent)

    /**
     * Configures apply block
     */
    public fun apply(init: ApplyDsl.() -> Unit) {
        block("apply") {
            ApplyDsl(this).apply(init)
        }
    }

    /**
     * Configures repositories
     */
    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    /**
     * Configures dependencies
     */
    public fun dependencies(init: DependencyHandlerDsl.() -> Unit) {
        block("dependencies") {
            DependencyHandlerDsl(this).apply(init)
        }
    }

    /**
     * Creates a property for subprojects
     */
    public fun property(name: String, value: String) {
        line("project.extra[${formatValue(name)}] = $value")
    }
}

/**
 * DSL for apply block
 */
public class ApplyDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Applies a plugin
     */
    public fun plugin(id: String) {
        line("plugin(${formatValue(id)})")
    }

}