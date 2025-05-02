package io.github.zenhelix.gradle.assertion

import io.github.zenhelix.gradle.exist
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions.assertThat
import org.gradle.api.Project

public class ProjectAssert(actual: Project) : AbstractAssert<ProjectAssert, Project>(actual, ProjectAssert::class.java) {

    public companion object {
        public fun assertThat(actual: Project): ProjectAssert = ProjectAssert(actual)

        private const val API: String = "api"
        private const val IMPLEMENTATION: String = "implementation"
        private const val TEST_IMPLEMENTATION: String = "testImplementation"
        private const val COMPILE_ONLY: String = "compileOnly"
        private const val TEST_COMPILE_ONLY: String = "testCompileOnly"
        private const val RUNTIME_ONLY: String = "runtimeOnly"
        private const val TEST_RUNTIME_ONLY: String = "testRuntimeOnly"
        private const val ANNOTATION_PROCESSOR: String = "annotationProcessor"
        private const val KAPT: String = "kapt"
    }

    public fun hasConfiguration(configuration: String): ProjectAssert = apply {
        assertThat(actual.configurations.findByName(configuration))
            .describedAs("Configuration '$configuration' should exist")
            .isNotNull
    }

    public fun containsDependency(configuration: String, group: String?, module: String, version: String? = null): ProjectAssert = apply {
        hasConfiguration(configuration)
        assertThat(actual.configurations.getByName(configuration).dependencies.exist(group, module, version))
            .describedAs("Configuration '$configuration' should contain dependency '$group:$module${version?.let { ":$it" } ?: ""}'")
            .isTrue()
    }

    public fun doesNotContainDependency(configuration: String, group: String?, module: String, version: String? = null): ProjectAssert = apply {
        hasConfiguration(configuration)
        assertThat(actual.configurations.getByName(configuration).dependencies.exist(group, module, version))
            .describedAs("Configuration '$configuration' should not contain dependency '$group:$module${version?.let { ":$it" } ?: ""}'")
            .isFalse()
    }

    public fun implementationContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(IMPLEMENTATION, group, module, version)

    public fun runtimeOnlyContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(RUNTIME_ONLY, group, module, version)

    public fun testImplementationContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(TEST_IMPLEMENTATION, group, module, version)

    public fun annotationProcessorContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(ANNOTATION_PROCESSOR, group, module, version)

    public fun apiContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(API, group, module, version)

    public fun compileOnlyContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(COMPILE_ONLY, group, module, version)

    public fun testRuntimeOnlyContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(TEST_RUNTIME_ONLY, group, module, version)

    public fun testCompileOnlyContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(TEST_COMPILE_ONLY, group, module, version)

    public fun kaptContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency(KAPT, group, module, version)

    public fun implementationDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(IMPLEMENTATION, group, module, version)

    public fun runtimeOnlyDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(RUNTIME_ONLY, group, module, version)

    public fun testImplementationDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(TEST_IMPLEMENTATION, group, module, version)

    public fun annotationProcessorDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(ANNOTATION_PROCESSOR, group, module, version)

    public fun apiDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(API, group, module, version)

    public fun compileOnlyDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(COMPILE_ONLY, group, module, version)

    public fun testRuntimeOnlyDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(TEST_RUNTIME_ONLY, group, module, version)

    public fun testCompileOnlyDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(TEST_COMPILE_ONLY, group, module, version)

    public fun kaptDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency(KAPT, group, module, version)

    public fun configurationIsEmpty(configuration: String): ProjectAssert = apply {
        hasConfiguration(configuration)
        assertThat(actual.configurations.getByName(configuration).dependencies)
            .describedAs("Configuration '$configuration' should be empty")
            .isEmpty()
    }

    public fun implementationIsEmpty(): ProjectAssert = configurationIsEmpty(IMPLEMENTATION)
    public fun runtimeOnlyIsEmpty(): ProjectAssert = configurationIsEmpty(RUNTIME_ONLY)
    public fun testImplementationIsEmpty(): ProjectAssert = configurationIsEmpty(TEST_IMPLEMENTATION)
    public fun annotationProcessorIsEmpty(): ProjectAssert = configurationIsEmpty(ANNOTATION_PROCESSOR)
    public fun apiIsEmpty(): ProjectAssert = configurationIsEmpty(API)
    public fun compileOnlyIsEmpty(): ProjectAssert = configurationIsEmpty(COMPILE_ONLY)
    public fun testRuntimeOnlyIsEmpty(): ProjectAssert = configurationIsEmpty(TEST_RUNTIME_ONLY)
    public fun testCompileOnlyIsEmpty(): ProjectAssert = configurationIsEmpty(TEST_COMPILE_ONLY)
    public fun kaptIsEmpty(): ProjectAssert = configurationIsEmpty(KAPT)

    public fun allConfigurationsAreEmpty(): ProjectAssert = apply {
        assertThat(actual.configurations.flatMap { it.allDependencies }).isEmpty()
    }

    public fun configurationIsNotEmpty(configuration: String): ProjectAssert = apply {
        hasConfiguration(configuration)
        assertThat(actual.configurations.getByName(configuration).dependencies)
            .describedAs("Configuration '$configuration' should not be empty")
            .isNotEmpty()
    }

    public fun implementationIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(IMPLEMENTATION)
    public fun runtimeOnlyIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(RUNTIME_ONLY)
    public fun testImplementationIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(TEST_IMPLEMENTATION)
    public fun annotationProcessorIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(ANNOTATION_PROCESSOR)
    public fun apiIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(API)
    public fun compileOnlyIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(COMPILE_ONLY)
    public fun testRuntimeOnlyIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(TEST_RUNTIME_ONLY)
    public fun testCompileOnlyIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(TEST_COMPILE_ONLY)
    public fun kaptIsNotEmpty(): ProjectAssert = configurationIsNotEmpty(KAPT)

    public fun configurationContainsExactly(configuration: String, vararg notions: String): ProjectAssert = apply {
        hasConfiguration(configuration)
        assertThat(actual.configurations.getByName(configuration).dependencies.map {
            listOfNotNull(it.group, it.name, it.version).joinToString(":")
        }).containsExactlyInAnyOrder(*notions)
    }

    public fun implementationContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(IMPLEMENTATION, *notions)

    public fun runtimeOnlyContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(RUNTIME_ONLY, *notions)

    public fun testRuntimeOnlyContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(TEST_RUNTIME_ONLY, *notions)

    public fun testImplementationContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(TEST_IMPLEMENTATION, *notions)

    public fun annotationProcessorContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(ANNOTATION_PROCESSOR, *notions)

    public fun apiContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(API, *notions)

    public fun compileOnlyContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(COMPILE_ONLY, *notions)

    public fun testCompileOnlyContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(TEST_COMPILE_ONLY, *notions)

    public fun kaptContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly(KAPT, *notions)

    public fun containsPlugin(pluginId: String): ProjectAssert = apply {
        assertThat(actual.plugins.hasPlugin(pluginId))
            .describedAs("Project should have plugin '$pluginId'")
            .isTrue()
    }

    public fun doesNotContainPlugin(pluginId: String): ProjectAssert = apply {
        assertThat(actual.plugins.hasPlugin(pluginId))
            .describedAs("Project should not have plugin '$pluginId'")
            .isFalse()
    }

    public fun containsTask(taskName: String): ProjectAssert = apply {
        assertThat(actual.tasks.findByName(taskName))
            .describedAs("Project should have task '$taskName'")
            .isNotNull()
    }

    public fun doesNotContainTask(taskName: String): ProjectAssert = apply {
        assertThat(actual.tasks.findByName(taskName))
            .describedAs("Project should not have task '$taskName'")
            .isNull()
    }

    public fun containsProperty(propertyName: String): ProjectAssert = apply {
        assertThat(actual.hasProperty(propertyName))
            .describedAs("Project should have property '$propertyName'")
            .isTrue()
    }

    public fun doesNotContainProperty(propertyName: String): ProjectAssert = apply {
        assertThat(actual.hasProperty(propertyName))
            .describedAs("Project should not have property '$propertyName'")
            .isFalse()
    }

    public fun propertyHasValue(propertyName: String, expectedValue: Any?): ProjectAssert = apply {
        containsProperty(propertyName)
        assertThat(actual.property(propertyName))
            .describedAs("Property '$propertyName' should have value '$expectedValue'")
            .isEqualTo(expectedValue)
    }

    public fun configurationsHasSize(expectedCount: Int): ProjectAssert = apply {
        assertThat(actual.configurations)
            .describedAs("Project should have $expectedCount configurations")
            .hasSize(expectedCount)
    }

    public fun configurationHasDependenciesSize(configuration: String, expectedCount: Int): ProjectAssert = apply {
        hasConfiguration(configuration)
        assertThat( actual.configurations.getByName(configuration).dependencies)
            .describedAs("Configuration '$configuration' should have $expectedCount dependencies")
            .hasSize(expectedCount)
    }

    public fun pluginsHasSize(expectedCount: Int): ProjectAssert = apply {
        assertThat(actual.plugins.size)
            .describedAs("Project should have $expectedCount plugins")
            .isEqualTo(expectedCount)
    }

    public fun tasksHasSize(expectedCount: Int): ProjectAssert = apply {
        assertThat(actual.tasks)
            .describedAs("Project should have $expectedCount tasks")
            .hasSize(expectedCount)
    }

    public fun hasName(expectedName: String): ProjectAssert = apply {
        assertThat(actual.name)
            .describedAs("Project should have name '$expectedName'")
            .isEqualTo(expectedName)
    }

    public fun hasGroup(expectedGroup: String): ProjectAssert = apply {
        assertThat(actual.group.toString())
            .describedAs("Project should have group '$expectedGroup'")
            .isEqualTo(expectedGroup)
    }

    public fun hasVersion(expectedVersion: String): ProjectAssert = apply {
        assertThat(actual.version.toString())
            .describedAs("Project should have version '$expectedVersion'")
            .isEqualTo(expectedVersion)
    }

}