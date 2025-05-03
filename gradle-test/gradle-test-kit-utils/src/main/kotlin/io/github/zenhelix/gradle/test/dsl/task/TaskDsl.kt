package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * DSL for Gradle tasks
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
    public fun dependsOn(task: String) {
        line("dependsOn(\"$task\")")
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