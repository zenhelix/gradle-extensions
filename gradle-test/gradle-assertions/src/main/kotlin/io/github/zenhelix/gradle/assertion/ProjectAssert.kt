package io.github.zenhelix.gradle.assertion

import io.github.zenhelix.gradle.exist
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.DependencySet

public class ProjectAssert(actual: Project) : AbstractAssert<ProjectAssert, Project>(actual, ProjectAssert::class.java) {

    public companion object {
        public fun assertThat(actual: Project): ProjectAssert = ProjectAssert(actual)
    }

    public fun containsDependency(configuration: String, group: String?, module: String, version: String? = null): ProjectAssert = apply {
        val config = actual.configurations.findByName(configuration)
        assertThat(config).isNotNull
        assertThat(config!!.dependencies.exist(group, module, version))
            .describedAs("Configuration '$configuration' should contain dependency '$group:$module${version?.let { ":$it" } ?: ""}'")
            .isNotNull().isTrue()
    }

    public fun doesNotContainDependency(configuration: String, group: String?, module: String, version: String? = null): ProjectAssert = apply {
        val config = actual.configurations.findByName(configuration)
        assertThat(config).isNotNull
            assertThat(config!!.dependencies.exist(group, module, version))
                .describedAs("Configuration '$configuration' should not contain dependency '$group:$module${version?.let { ":$it" } ?: ""}'")
                .isFalse()
    }

    public fun implementationContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("implementation", group, module, version)

    public fun runtimeOnlyContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("runtimeOnly", group, module, version)

    public fun testImplementationContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("testImplementation", group, module, version)

    public fun annotationProcessorContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("annotationProcessor", group, module, version)

    public fun apiContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("api", group, module, version)

    public fun compileOnlyContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("compileOnly", group, module, version)

    public fun testRuntimeOnlyContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("testRuntimeOnly", group, module, version)

    public fun testCompileOnlyContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("testCompileOnly", group, module, version)

    public fun kaptContains(group: String?, module: String, version: String? = null): ProjectAssert =
        containsDependency("kapt", group, module, version)

    public fun implementationDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("implementation", group, module, version)

    public fun runtimeOnlyDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("runtimeOnly", group, module, version)

    public fun testImplementationDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("testImplementation", group, module, version)

    public fun annotationProcessorDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("annotationProcessor", group, module, version)

    public fun apiDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("api", group, module, version)

    public fun compileOnlyDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("compileOnly", group, module, version)

    public fun testRuntimeOnlyDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("testRuntimeOnly", group, module, version)

    public fun testCompileOnlyDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("testCompileOnly", group, module, version)

    public fun kaptDoesNotContain(group: String?, module: String, version: String? = null): ProjectAssert =
        doesNotContainDependency("kapt", group, module, version)

    public fun configurationIsEmpty(configuration: String): ProjectAssert = apply {
        assertThat(actual.configurations.findByName(configuration)?.dependencies?.isEmpty())
            .describedAs("Configuration '$configuration' should be empty")
            .isNotNull().isTrue()
    }

    public fun implementationIsEmpty(): ProjectAssert = configurationIsEmpty("implementation")
    public fun runtimeOnlyIsEmpty(): ProjectAssert = configurationIsEmpty("runtimeOnly")
    public fun testImplementationIsEmpty(): ProjectAssert = configurationIsEmpty("testImplementation")
    public fun annotationProcessorIsEmpty(): ProjectAssert = configurationIsEmpty("annotationProcessor")
    public fun apiIsEmpty(): ProjectAssert = configurationIsEmpty("api")
    public fun compileOnlyIsEmpty(): ProjectAssert = configurationIsEmpty("compileOnly")
    public fun testRuntimeOnlyIsEmpty(): ProjectAssert = configurationIsEmpty("testRuntimeOnly")
    public fun testCompileOnlyIsEmpty(): ProjectAssert = configurationIsEmpty("testCompileOnly")
    public fun kaptIsEmpty(): ProjectAssert = configurationIsEmpty("kapt")

    public fun allConfigurationsAreEmpty(): ProjectAssert = apply {
        assertThat(actual.configurations.flatMap { it.allDependencies }).isEmpty()
    }

    public fun configurationIsNotEmpty(configuration: String): ProjectAssert = apply {
        assertThat(actual.configurations.findByName(configuration)?.dependencies?.isEmpty())
            .describedAs("Configuration '$configuration' should not be empty")
            .isNotNull().isFalse()
    }

    public fun implementationIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("implementation")
    public fun runtimeOnlyIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("runtimeOnly")
    public fun testImplementationIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("testImplementation")
    public fun annotationProcessorIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("annotationProcessor")
    public fun apiIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("api")
    public fun compileOnlyIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("compileOnly")
    public fun testRuntimeOnlyIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("testRuntimeOnly")
    public fun testCompileOnlyIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("testCompileOnly")
    public fun kaptIsNotEmpty(): ProjectAssert = configurationIsNotEmpty("kapt")

    public fun configurationContainsExactly(configuration: String, vararg notions: String): ProjectAssert = apply {
        assertThat(actual.configurations.getByName(configuration).dependencies.map {
            listOfNotNull(it.group, it.name, it.version).joinToString(":")
        }).containsExactlyInAnyOrder(*notions)
    }

    public fun implementationContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("implementation", *notions)

    public fun runtimeOnlyContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("runtimeOnly", *notions)

    public fun testRuntimeOnlyContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("testRuntimeOnly", *notions)

    public fun testImplementationContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("testImplementation", *notions)

    public fun annotationProcessorContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("annotationProcessor", *notions)

    public fun apiContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("api", *notions)

    public fun compileOnlyContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("compileOnly", *notions)

    public fun testCompileOnlyContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("testCompileOnly", *notions)

    public fun kaptContainsExactly(vararg notions: String): ProjectAssert =
        configurationContainsExactly("kapt", *notions)

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
        assertThat(actual.configurations.findByName(configuration)?.dependencies)
            .describedAs("Configuration '$configuration' should have $expectedCount dependencies")
            .isNotNull().hasSize(expectedCount)
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