package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * DSL for project configuration
 */
public class ProjectConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the project group
     */
    public fun group(value: String) {
        line("group = \"$value\"")
    }

    /**
     * Sets the project version
     */
    public fun version(value: String) {
        line("version = \"$value\"")
    }

    /**
     * Sets the project description
     */
    public fun description(value: String) {
        line("description = \"$value\"")
    }

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
        line("project.extra[\"$name\"] = $value")
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
        line("plugin(\"$id\")")
    }

}
