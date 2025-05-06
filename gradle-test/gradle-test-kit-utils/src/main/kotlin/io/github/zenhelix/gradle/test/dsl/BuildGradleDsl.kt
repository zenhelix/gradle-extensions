package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.gradle.DependencyHandlerDsl
import io.github.zenhelix.gradle.test.dsl.gradle.ProjectConfigDsl
import io.github.zenhelix.gradle.test.dsl.gradle.RepositoryHandlerDsl
import io.github.zenhelix.gradle.test.dsl.task.JavaDsl
import io.github.zenhelix.gradle.test.dsl.task.PublishingDsl
import io.github.zenhelix.gradle.test.dsl.task.SigningDsl
import io.github.zenhelix.gradle.test.dsl.task.TaskDsl
import io.github.zenhelix.gradle.test.dsl.task.TasksDsl
import io.github.zenhelix.gradle.test.dsl.task.android.AndroidLibraryDsl
import io.github.zenhelix.gradle.test.dsl.task.kotlin.KotlinMultiplatformDsl

/**
 * Improved DSL for building build.gradle.kts content
 */
public class BuildGradleDsl : GradleDslImpl() {

    // Lazy initialized tasks DSL
    private val tasksDsl by lazy { TasksDsl(this) }

    /**
     * Access to the tasks container
     */
    public fun tasks(init: TasksDsl.() -> Unit) {
        tasksDsl.apply(init)
    }

    /**
     * Adds multiple import statements
     */
    public fun import(vararg paths: String) {
        paths.forEach { path ->
            line("import $path")
        }
        if (paths.isNotEmpty()) {
            line("")
        }
    }

    /**
     * Configures plugins block
     */
    public fun plugins(init: PluginsDsl.() -> Unit) {
        block("plugins") {
            withDsl(PluginsDsl(this), init)
        }
    }

    /**
     * Configures buildscript block
     */
    public fun buildscript(init: BuildscriptDsl.() -> Unit) {
        block("buildscript") {
            BuildscriptDsl(this).apply(init)
        }
    }

    public var group: String by PropertyDelegate(this)

    /**
     * Sets the project version
     */
    public var version: String by PropertyDelegate(this)

    /**
     * Configures allprojects block
     */
    public fun allprojects(init: ProjectConfigDsl.() -> Unit) {
        block("allprojects") {
            ProjectConfigDsl(this).apply(init)
        }
    }

    /**
     * Configures subprojects block
     */
    public fun subprojects(init: ProjectConfigDsl.() -> Unit) {
        block("subprojects") {
            ProjectConfigDsl(this).apply(init)
        }
    }

    /**
     * Creates a configure block with filter
     */
    public fun configure(filter: String, init: ProjectConfigDsl.() -> Unit) {
        block("configure($filter)") {
            ProjectConfigDsl(this).apply(init)
        }
    }

    /**
     * Adds a val variable declaration
     */
    public fun variable(name: String, type: String, value: String) {
        line("val $name: $type = $value")
    }

    /**
     * Adds a variable with inferred type
     */
    public fun variable(name: String, value: String) {
        line("val $name = $value")
    }

    /**
     * Adds a project property
     */
    public fun property(name: String, value: String) {
        line("project.extra[${formatValue(name)}] = $value")
    }

    /**
     * Configures dependencies block
     */
    public fun dependencies(init: DependencyHandlerDsl.() -> Unit) {
        block("dependencies") {
            DependencyHandlerDsl(this).apply(init)
        }
    }

    private val publishingDsl = PublishingDsl(this)

    /**
     * Configures publishing block
     */
    public fun publishing(init: PublishingDsl.() -> Unit) {
        block("publishing") {
            PublishingDsl(this).apply(init)
        }
    }

    public val publishing: PublishingDsl
        get() = publishingDsl

    /**
     * Configures signing block
     */
    public fun signing(init: SigningDsl.() -> Unit) {
        block("signing") {
            SigningDsl(this).apply(init)
        }
    }

    /**
     * Configures java block
     */
    public fun java(init: JavaDsl.() -> Unit) {
        block("java") {
            JavaDsl(this).apply(init)
        }
    }

    /**
     * Configures kotlin block for multiplatform
     */
    public fun kotlinMultiplatform(init: KotlinMultiplatformDsl.() -> Unit) {
        block("kotlin") {
            KotlinMultiplatformDsl(this).apply(init)
        }
    }

    /**
     * Configures android block
     */
    public fun androidLibrary(init: AndroidLibraryDsl.() -> Unit) {
        block("android") {
            AndroidLibraryDsl(this).apply(init)
        }
    }

    /**
     * Registers a task - convenience method that delegates to tasks.register
     */
    public fun task(name: String, init: TaskDsl.() -> Unit = {}) {
        tasksDsl.register(name, init)
    }

    /**
     * Registers a typed task - convenience method that delegates to tasks.register
     */
    public fun task(name: String, type: String, init: TaskDsl.() -> Unit = {}) {
        tasksDsl.register(name, type, init)
    }

    /**
     * Adds a project afterEvaluate block
     */
    public fun afterEvaluate(init: GradleDsl.() -> Unit) {
        block("afterEvaluate") {
            init(this)
        }
    }

    /**
     * Configures repositories block
     */
    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    /**
     * Adds an apply() function call
     */
    public fun apply(plugin: String) {
        line("apply(plugin = ${formatValue(plugin)})")
    }
}

/**
 * DSL for buildscript block
 */
public class BuildscriptDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures repositories for buildscript
     */
    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    /**
     * Configures dependencies for buildscript
     */
    public fun dependencies(init: DependencyHandlerDsl.() -> Unit) {
        block("dependencies") {
            DependencyHandlerDsl(this).apply(init)
        }
    }
}

/**
 * DSL for plugins in build.gradle.kts
 */
public class PluginsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a plugin with id and optional version
     */
    public fun id(pluginId: String, version: String? = null) {
        if (version != null) {
            line("id(${formatValue(pluginId)}) version ${formatValue(version)}")
        } else {
            line("id(${formatValue(pluginId)})")
        }
    }

    /**
     * Adds a built-in plugin
     */
    public fun builtin(name: String) {
        line("`$name`")
    }

    /**
     * Adds Java library plugin
     */
    public fun javaLibrary() {
        builtin("java-library")
    }

    /**
     * Adds Java platform plugin
     */
    public fun javaPlatform() {
        builtin("java-platform")
    }

    /**
     * Adds version catalog plugin
     */
    public fun versionCatalog() {
        builtin("version-catalog")
    }

    /**
     * Adds Maven publish plugin
     */
    public fun mavenPublish() {
        builtin("maven-publish")
    }

    /**
     * Adds signing plugin
     */
    public fun signing() {
        builtin("signing")
    }

    /**
     * Adds application plugin
     */
    public fun application() {
        builtin("application")
    }

    /**
     * Adds Gradle publish plugin
     */
    public fun gradlePublish(version: String? = null) {
        id("com.gradle.plugin-publish", version)
    }

    /**
     * Adds kotlin plugin
     */
    public fun kotlin(target: String, version: String? = null) {
        if (version != null) {
            line("kotlin(${formatValue(target)}) version ${formatValue(version)}")
        } else {
            line("kotlin(${formatValue(target)})")
        }
    }

    public fun kotlinJvm(version: String? = null) {
        kotlin("jvm", version)
    }

    /**
     * Adds kotlin multiplatform plugin
     */
    public fun kotlinMultiplatform(version: String? = null) {
        kotlin("multiplatform", version)
    }

    /**
     * Adds Kotlin serialization plugin
     */
    public fun kotlinSerialization(version: String? = null) {
        kotlin("plugin.serialization", version)
    }

    /**
     * Adds Kotlin parcelize plugin
     */
    public fun kotlinParcelize() {
        kotlin("plugin.parcelize")
    }

    /**
     * Adds Kotlin kapt plugin
     */
    public fun kotlinKapt(version: String? = null) {
        kotlin("kapt", version)
    }

    /**
     * Adds android application plugin
     */
    public fun androidApplication(version: String? = null) {
        id("com.android.application", version)
    }

    /**
     * Adds android library plugin
     */
    public fun androidLibrary(version: String? = null) {
        id("com.android.library", version)
    }
}

/**
 * Creates and configures build.gradle.kts DSL
 */
public fun buildGradleKts(init: BuildGradleDsl.() -> Unit): String {
    return BuildGradleDsl().apply(init).build()
}