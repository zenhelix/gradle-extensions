package io.github.zenhelix.gradle.testkit

import io.github.zenhelix.gradle.testkit.parser.GradleOutput
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions.assertThat

public class GradleTasksOutputAssert(actual: GradleOutput) :
    AbstractAssert<GradleTasksOutputAssert, GradleOutput>(actual, GradleTasksOutputAssert::class.java) {

    public companion object {
        public fun assertThat(actual: GradleOutput): GradleTasksOutputAssert = GradleTasksOutputAssert(actual)
    }

    public fun hasCategory(vararg categoryName: String): GradleTasksOutputAssert = apply {
        assertThat(actual.categories.map { it.name }).contains(*categoryName)
    }

    public fun hasOnlyCategory(vararg categoryName: String): GradleTasksOutputAssert = apply {
        assertThat(actual.categories.map { it.name }).containsExactlyInAnyOrder(*categoryName)
    }

    public fun hasTask(vararg taskName: String): GradleTasksOutputAssert = apply {
        assertThat(actual.categories.flatMap { it.tasks }.map { it.name }).contains(*taskName)
    }

    public fun hasOnlyTask(vararg taskName: String): GradleTasksOutputAssert = apply {
        assertThat(actual.categories.flatMap { it.tasks }.map { it.name }).containsExactlyInAnyOrder(*taskName)
    }

    public fun hasTasksInCategory(categoryName: String, vararg taskNames: String): GradleTasksOutputAssert = apply {
        assertThat(actual.categories.map { it.name }).contains(categoryName)
        assertThat(actual.categories.first { it.name == categoryName }.tasks.map { it.name }).contains(*taskNames)
    }

    public fun hasOnlyTasksInCategory(categoryName: String, vararg taskNames: String): GradleTasksOutputAssert = apply {
        assertThat(actual.categories.map { it.name }).contains(categoryName)
        assertThat(actual.categories.first { it.name == categoryName }.tasks.map { it.name }).containsExactlyInAnyOrder(*taskNames)
    }

    public fun hasRule(vararg pattern: String): GradleTasksOutputAssert = apply {
        assertThat(actual.rules.map { it.pattern }).contains(*pattern)
    }

    public fun hasOnlyRule(vararg pattern: String): GradleTasksOutputAssert = apply {
        assertThat(actual.rules.map { it.pattern }).containsExactlyInAnyOrder(*pattern)
    }

    public fun doesNotHaveRule(vararg pattern: String): GradleTasksOutputAssert = apply {
        assertThat(actual.rules.map { it.pattern }).doesNotContain(*pattern)
    }

    public fun hasRuleWithDescription(pattern: String, description: String): GradleTasksOutputAssert = apply {
        assertThat(actual.rules.map { it.pattern }).contains(pattern)
        assertThat(actual.rules.first { it.pattern == pattern }.description).isEqualTo(description)
    }

    public fun hasNoRules(): GradleTasksOutputAssert = apply {
        assertThat(actual.rules).isEmpty()
    }

    public fun hasNoCategories(): GradleTasksOutputAssert = apply {
        assertThat(actual.categories).isEmpty()
    }

    public fun hasTaskWithDescription(taskName: String, description: String): GradleTasksOutputAssert = apply {
        val task = actual.categories.flatMap { it.tasks }.find { it.name == taskName }
        assertThat(task).isNotNull()
        assertThat(task!!.description).isEqualTo(description)
    }

    public fun hasCategoryWithTaskCount(categoryName: String, taskCount: Int): GradleTasksOutputAssert = apply {
        assertThat(actual.categories.map { it.name }).contains(categoryName)
        assertThat(actual.categories.first { it.name == categoryName }.tasks).hasSize(taskCount)
    }

    public fun hasTaskCount(expectedCount: Int): GradleTasksOutputAssert = apply {
        assertThat(actual.categories.flatMap { it.tasks }).hasSize(expectedCount)
    }

    public fun hasCategoryCount(expectedCount: Int): GradleTasksOutputAssert = apply {
        assertThat(actual.categories).hasSize(expectedCount)
    }

    public fun hasRuleCount(expectedCount: Int): GradleTasksOutputAssert = apply {
        assertThat(actual.rules).hasSize(expectedCount)
    }
}