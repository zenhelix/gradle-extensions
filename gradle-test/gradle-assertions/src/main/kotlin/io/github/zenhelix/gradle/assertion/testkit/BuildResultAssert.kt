package io.github.zenhelix.gradle.assertion.testkit

import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions.assertThat
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.BuildTask
import org.gradle.testkit.runner.TaskOutcome

public class BuildResultAssert(actual: BuildResult) : AbstractAssert<BuildResultAssert, BuildResult>(actual, BuildResultAssert::class.java) {

    public companion object {
        public fun assertThat(actual: BuildResult): BuildResultAssert = BuildResultAssert(actual)

        private const val BUILD_SUCCESSFUL_TEXT = "BUILD SUCCESSFUL"
        private const val BUILD_FAILED_TEXT = "BUILD FAILED"
    }

    public fun isSuccessful(): BuildResultAssert = apply {
        outputContains(BUILD_SUCCESSFUL_TEXT)
    }

    public fun isFailed(): BuildResultAssert = apply {
        outputContains(BUILD_FAILED_TEXT)
    }

    public fun outputContains(text: String): BuildResultAssert = apply {
        assertThat(actual.output).contains(text)
    }

    public fun outputDoesNotContain(text: String): BuildResultAssert = apply {
        assertThat(actual.output).doesNotContain(text)
    }

    public fun containsSuccessTasks(module: String, vararg task: String): BuildResultAssert = apply {
        containsTasksByStatus(module = module, status = TaskOutcome.SUCCESS, task = task)
    }

    public fun containsFailedTasks(module: String, vararg task: String): BuildResultAssert = apply {
        containsTasksByStatus(module = module, status = TaskOutcome.FAILED, task = task)
    }

    public fun containsUpToDateTasks(module: String, vararg task: String): BuildResultAssert = apply {
        containsTasksByStatus(module = module, status = TaskOutcome.UP_TO_DATE, task = task)
    }

    public fun containsSkippedTasks(module: String, vararg task: String): BuildResultAssert = apply {
        containsTasksByStatus(module = module, status = TaskOutcome.SKIPPED, task = task)
    }

    public fun containsFromCacheTasks(module: String, vararg task: String): BuildResultAssert = apply {
        containsTasksByStatus(module = module, status = TaskOutcome.FROM_CACHE, task = task)
    }

    public fun containsNoSourceTasks(module: String, vararg task: String): BuildResultAssert = apply {
        containsTasksByStatus(module = module, status = TaskOutcome.NO_SOURCE, task = task)
    }

    public fun containsTasksByStatus(module: String, status: TaskOutcome, vararg task: String) {
        val tasksByStatus = GradleModule.of(actual).module(module).allTasksByStatus(status).map { it.name }
        assertThat(tasksByStatus).contains(*task)
    }

    // Task outcome assertions regardless of module
    public fun containsSuccessTasksInAnyModule(vararg task: String): BuildResultAssert = apply {
        containsTasksByStatusInAnyModule(status = TaskOutcome.SUCCESS, task = task)
    }

    public fun containsFailedTasksInAnyModule(vararg task: String): BuildResultAssert = apply {
        containsTasksByStatusInAnyModule(status = TaskOutcome.FAILED, task = task)
    }

    public fun containsUpToDateTasksInAnyModule(vararg task: String): BuildResultAssert = apply {
        containsTasksByStatusInAnyModule(status = TaskOutcome.UP_TO_DATE, task = task)
    }

    public fun containsSkippedTasksInAnyModule(vararg task: String): BuildResultAssert = apply {
        containsTasksByStatusInAnyModule(status = TaskOutcome.SKIPPED, task = task)
    }

    public fun containsFromCacheTasksInAnyModule(vararg task: String): BuildResultAssert = apply {
        containsTasksByStatusInAnyModule(status = TaskOutcome.FROM_CACHE, task = task)
    }

    public fun containsNoSourceTasksInAnyModule(vararg task: String): BuildResultAssert = apply {
        containsTasksByStatusInAnyModule(status = TaskOutcome.NO_SOURCE, task = task)
    }

    public fun containsTasksByStatusInAnyModule(status: TaskOutcome, vararg task: String) {
        val allTasksByStatus = GradleModule.of(actual).allTasks().filter { it.result == status }.map { it.name }
        assertThat(allTasksByStatus).contains(*task)
    }

    // Negative task assertions
    public fun doesNotContainSuccessTasks(module: String, vararg task: String): BuildResultAssert = apply {
        doesNotContainTasksNotByStatus(module = module, status = TaskOutcome.SUCCESS, task = task)
    }

    public fun doesNotContainFailedTasks(module: String, vararg task: String): BuildResultAssert = apply {
        doesNotContainTasksNotByStatus(module = module, status = TaskOutcome.FAILED, task = task)
    }

    public fun doesNotContainUpToDateTasks(module: String, vararg task: String): BuildResultAssert = apply {
        doesNotContainTasksNotByStatus(module = module, status = TaskOutcome.UP_TO_DATE, task = task)
    }

    public fun doesNotContainSkippedTasks(module: String, vararg task: String): BuildResultAssert = apply {
        doesNotContainTasksNotByStatus(module = module, status = TaskOutcome.SKIPPED, task = task)
    }

    public fun doesNotContainFromCacheTasks(module: String, vararg task: String): BuildResultAssert = apply {
        doesNotContainTasksNotByStatus(module = module, status = TaskOutcome.FROM_CACHE, task = task)
    }

    public fun doesNotContainNoSourceTasks(module: String, vararg task: String): BuildResultAssert = apply {
        doesNotContainTasksNotByStatus(module = module, status = TaskOutcome.NO_SOURCE, task = task)
    }

    public fun doesNotContainTasksNotByStatus(module: String, status: TaskOutcome, vararg task: String) {
        val tasksByStatus = GradleModule.of(actual).module(module).allTasksByStatus(status).map { it.name }
        assertThat(tasksByStatus).doesNotContain(*task)
    }

    // Task existence assertions
    public fun containsTasks(module: String, vararg taskName: String): BuildResultAssert = apply {
        val moduleTasks = GradleModule.of(actual).module(module).allTasks().map { it.name }
        assertThat(moduleTasks).contains(*taskName)
    }

    public fun doesNotContainTasks(module: String, vararg taskName: String): BuildResultAssert = apply {
        val moduleTasks = GradleModule.of(actual).module(module).allTasks().map { it.name }
        assertThat(moduleTasks).doesNotContain(*taskName)
    }

    public fun containsOnlyTasks(module: String, vararg taskName: String): BuildResultAssert = apply {
        val moduleTasks = GradleModule.of(actual).module(module).allTasks().map { it.name }
        assertThat(moduleTasks).containsExactlyInAnyOrder(*taskName)
    }

    // Module assertions
    public fun containsModule(vararg moduleName: String): BuildResultAssert = apply {
        val modules = GradleModule.of(actual).allModules().map { it.name }
        assertThat(modules).contains(*moduleName)
    }

    public fun doesNotContainModule(vararg moduleName: String): BuildResultAssert = apply {
        val modules = GradleModule.of(actual).allModules().map { it.name }
        assertThat(modules).doesNotContain(*moduleName)
    }

    // Task count assertions
    public fun moduleTasksHasSize(module: String, expectedCount: Int): BuildResultAssert = apply {
        val moduleTasks = GradleModule.of(actual).module(module).allTasks()
        assertThat(moduleTasks).hasSize(expectedCount)
    }

    public fun tasksHasSize(expectedCount: Int): BuildResultAssert = apply {
        assertThat(actual.tasks).hasSize(expectedCount)
    }

    public fun modulesHasSize(expectedCount: Int): BuildResultAssert = apply {
        val modules = GradleModule.of(actual).allModules()
        assertThat(modules).hasSize(expectedCount)
    }

    public fun moduleTasksByStatusHasSize(module: String, status: TaskOutcome, expectedCount: Int): BuildResultAssert = apply {
        val tasksByStatus = GradleModule.of(actual).module(module).allTasksByStatus(status)
        assertThat(tasksByStatus).hasSize(expectedCount)
    }

    // Empty assertions
    public fun moduleIsEmpty(module: String): BuildResultAssert = apply {
        val moduleTasks = GradleModule.of(actual).module(module).allTasks()
        assertThat(moduleTasks).isEmpty()
    }

    public fun moduleTasksByStatusIsEmpty(module: String, status: TaskOutcome): BuildResultAssert = apply {
        val tasksByStatus = GradleModule.of(actual).module(module).allTasksByStatus(status)
        assertThat(tasksByStatus).isEmpty()
    }

}

private data class GradleTask(val name: String, val result: TaskOutcome)

private data class GradleModule(
    val name: String,
    private val _submodules: MutableMap<String, GradleModule> = mutableMapOf(),
    private val _tasks: MutableList<GradleTask> = mutableListOf()
) {
    val submodules: Map<String, GradleModule> get() = _submodules
    val tasks: List<GradleTask> get() = _tasks

    fun addTask(task: GradleTask) {
        _tasks.add(task)
    }

    fun getOrCreateChild(name: String): GradleModule = _submodules.getOrPut(name) { GradleModule(name) }

    fun allModules(): List<GradleModule> = buildList {
        add(this@GradleModule)
        submodules.values.forEach { child -> addAll(child.allModules()) }
    }

    fun module(name: String): GradleModule = findModuleByNameOrNull(name) ?: throw NoSuchElementException("Module with name '$name' not found")

    fun allTasks(): List<GradleTask> = buildList {
        addAll(tasks)
        submodules.values.forEach { addAll(it.allTasks()) }
    }

    fun tasksByStatus(status: TaskOutcome): List<GradleTask> = tasks.filter { it.result == status }
    fun allTasksByStatus(status: TaskOutcome): List<GradleTask> = allTasks().filter { it.result == status }

    fun moduleTasks(moduleName: String): List<GradleTask> = module(moduleName).tasks
    fun allModuleTasks(moduleName: String): List<GradleTask> = module(moduleName).allTasks()

    private fun findModuleByNameOrNull(name: String): GradleModule? {
        if (this.name == name) return this

        submodules[name]?.let { return it }

        for (child in submodules.values) {
            val found = child.findModuleByNameOrNull(name)
            if (found != null) return found
        }

        return null
    }

    companion object {
        fun of(result: BuildResult): GradleModule = of(result.tasks)
        fun of(tasks: List<BuildTask>): GradleModule {
            val root = GradleModule("root")

            tasks.forEach { task ->
                val parts = task.path.split(":")
                val moduleParts = parts.drop(1).dropLast(1)
                val taskName = parts.last()

                if (moduleParts.isEmpty()) {
                    root.addTask(GradleTask(taskName, task.outcome))
                    return@forEach
                }

                var currentModule = root
                for (modulePart in moduleParts) {
                    currentModule = currentModule.getOrCreateChild(modulePart)
                }

                currentModule.addTask(GradleTask(taskName, task.outcome))
            }

            return root
        }
    }

    override fun toString(): String = HierarchyPrinter().printHierarchy(this)

    private class HierarchyPrinter {
        private val builder = StringBuilder()

        fun printHierarchy(module: GradleModule, indent: String = ""): String {
            builder.clear()
            appendHierarchy(module, indent)
            return builder.toString()
        }

        private fun appendHierarchy(module: GradleModule, indent: String = "") {
            builder.appendLine("$indent${module.name}")

            module.tasks.forEach { task ->
                builder.appendLine("$indent  ├── ${task.name} (${task.result})")
            }

            val iterator = module.submodules.values.iterator()
            while (iterator.hasNext()) {
                val childModule = iterator.next()
                val isLast = !iterator.hasNext()
                val childIndent = if (isLast) "$indent  └──" else "$indent  ├──"
                builder.appendLine("$childIndent ${childModule.name}")

                val newIndent = if (isLast) "$indent      " else "$indent  │   "
                childModule.tasks.forEach { task ->
                    builder.appendLine("$newIndent├── ${task.name} (${task.result})")
                }

                val childIterator = childModule.submodules.values.iterator()
                while (childIterator.hasNext()) {
                    val grandChildModule = childIterator.next()
                    val isChildLast = !childIterator.hasNext()
                    appendHierarchy(grandChildModule, if (isChildLast) "$newIndent└── " else "$newIndent├── ")
                }
            }
        }
    }
}