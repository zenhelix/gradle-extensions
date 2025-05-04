package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractNamedDomainObjectCollectionDsl
import org.gradle.api.Task

/**
 * DSL for Gradle tasks
 */
public class TasksDsl(parent: GradleDsl) :
    AbstractNamedDomainObjectCollectionDsl<Task, TaskDsl>(parent, "tasks") {

    /**
     * Creates the configurator for a task
     */
    override fun createConfigurator(dsl: GradleDsl): TaskDsl = TaskDsl(dsl)

    /**
     * Registers a new task
     */
    public fun register(name: String, init: TaskDsl.() -> Unit = {}) {
        parent.block("tasks.register(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Registers a typed task
     */
    public fun register(name: String, type: String, init: TaskDsl.() -> Unit = {}) {
        parent.block("tasks.register<$type>(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

}

/**
 * DSL for Gradle tasks - core implementation remains the same
 */
public class TaskDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds doLast block
     */
    public fun doLast(init: TaskActionDsl.() -> Unit) {
        block("doLast") {
            TaskActionDsl(this).apply(init)
        }
    }

    /**
     * Adds doFirst block
     */
    public fun doFirst(init: TaskActionDsl.() -> Unit) {
        block("doFirst") {
            TaskActionDsl(this).apply(init)
        }
    }

    /**
     * Sets task dependencies
     */
    public fun dependsOn(vararg tasks: String) {
        if (tasks.size == 1) {
            line("dependsOn(\"${tasks[0]}\")")
        } else {
            val tasksStr = tasks.joinToString("\", \"", "\"", "\"")
            line("dependsOn($tasksStr)")
        }
    }

    /**
     * Sets task group
     */
    public fun group(name: String) {
        line("group = \"$name\"")
    }

    /**
     * Sets task description
     */
    public fun description(text: String) {
        line("description = \"$text\"")
    }

    /**
     * Sets the task type
     */
    public fun type(typeName: String) {
        line("type = $typeName::class.java")
    }

    /**
     * Sets the task enabled flag
     */
    public fun enabled(isEnabled: Boolean) {
        line("enabled = $isEnabled")
    }
}

/**
 * DSL for task actions
 */
public class TaskActionDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Creates file operations
     */
    public fun file(path: String, init: FileActionDsl.() -> Unit) {
        block("file(\"$path\").apply") {
            FileActionDsl(this).apply(init)
        }
    }

    /**
     * Prints a message
     */
    public fun println(message: String) {
        line("println(\"$message\")")
    }
}

/**
 * DSL for file actions
 */
public class FileActionDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Creates parent directories
     */
    public fun mkdirs() {
        line("parentFile.mkdirs()")
    }

    /**
     * Writes text to file
     */
    public fun writeText(text: String) {
        line("writeText(\"\"\"$text\"\"\")")
    }

    /**
     * Deletes the file
     */
    public fun delete() {
        line("delete()")
    }
}