package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractNamedDomainObjectContainerDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate
import org.gradle.api.Task

public class TasksDsl(parent: GradleDsl) : AbstractNamedDomainObjectContainerDsl<Task, TaskDsl>(parent, "tasks") {

    override fun createConfigurator(dsl: GradleDsl): TaskDsl = TaskDsl(dsl)

}

public class TaskDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun doLast(init: TaskActionDsl.() -> Unit) {
        block("doLast") {
            TaskActionDsl(this).apply(init)
        }
    }

    public fun doFirst(init: TaskActionDsl.() -> Unit) {
        block("doFirst") {
            TaskActionDsl(this).apply(init)
        }
    }

    public fun dependsOn(vararg tasks: String) {
        if (tasks.size == 1) {
            line("dependsOn(${formatValue(tasks[0])})")
        } else {
            val tasksStr = tasks.joinToString("\", \"", "\"", "\"")
            line("dependsOn($tasksStr)")
        }
    }

    public var group: String by PropertyDelegate(parent)

    public var description: String by PropertyDelegate(parent)

    public var type: String by PropertyDelegate(parent) { "$it::class.java" }

    public var enabled: Boolean by PropertyDelegate(parent)
}

public class TaskActionDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun file(path: String, init: FileActionDsl.() -> Unit) {
        block("file(${formatValue(path)}).apply") {
            FileActionDsl(this).apply(init)
        }
    }

    public fun println(message: String) {
        line("println(${formatValue(message)})")
    }
}

public class FileActionDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun mkdirs() {
        line("parentFile.mkdirs()")
    }

    public fun writeText(text: String) {
        line("writeText(${formatValue(text)})")
    }

    public fun delete() {
        line("delete()")
    }
}