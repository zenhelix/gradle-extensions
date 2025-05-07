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
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate

public class BuildGradleDsl : GradleDslImpl() {

    private val tasksDsl by lazy { TasksDsl(this) }

    public fun tasks(init: TasksDsl.() -> Unit) {
        tasksDsl.apply(init)
    }

    public fun import(vararg paths: String) {
        paths.forEach { path ->
            line("import $path")
        }
    }

    public fun plugins(init: PluginsDsl.() -> Unit) {
        block("plugins") {
            withDsl(PluginsDsl(this), init)
        }
    }

    public fun buildscript(init: BuildscriptDsl.() -> Unit) {
        block("buildscript") {
            BuildscriptDsl(this).apply(init)
        }
    }

    public var group: String by PropertyDelegate(this)

    public var version: String by PropertyDelegate(this)

    public fun allprojects(init: ProjectConfigDsl.() -> Unit) {
        block("allprojects") {
            ProjectConfigDsl(this).apply(init)
        }
    }

    public fun subprojects(init: ProjectConfigDsl.() -> Unit) {
        block("subprojects") {
            ProjectConfigDsl(this).apply(init)
        }
    }

    public fun configure(filter: String, init: ProjectConfigDsl.() -> Unit) {
        block("configure($filter)") {
            ProjectConfigDsl(this).apply(init)
        }
    }

    public fun variable(name: String, type: String, value: String) {
        line("val $name: $type = $value")
    }

    public fun variable(name: String, value: String) {
        line("val $name = $value")
    }

    public fun property(name: String, value: String) {
        line("project.extra[${formatValue(name)}] = $value")
    }

    public fun dependencies(init: DependencyHandlerDsl.() -> Unit) {
        block("dependencies") {
            DependencyHandlerDsl(this).apply(init)
        }
    }

    private val publishingDsl = PublishingDsl(this)

    public fun publishing(init: PublishingDsl.() -> Unit) {
        block("publishing") {
            PublishingDsl(this).apply(init)
        }
    }

    public val publishing: PublishingDsl
        get() = publishingDsl

    public fun signing(init: SigningDsl.() -> Unit) {
        block("signing") {
            SigningDsl(this).apply(init)
        }
    }

    public fun java(init: JavaDsl.() -> Unit) {
        block("java") {
            JavaDsl(this).apply(init)
        }
    }

    public fun kotlinMultiplatform(init: KotlinMultiplatformDsl.() -> Unit) {
        block("kotlin") {
            KotlinMultiplatformDsl(this).apply(init)
        }
    }

    public fun androidLibrary(init: AndroidLibraryDsl.() -> Unit) {
        block("android") {
            AndroidLibraryDsl(this).apply(init)
        }
    }

    public fun task(name: String, init: TaskDsl.() -> Unit = {}) {
        block("task(${formatValue(name)})") {
            TaskDsl(this).apply(init)
        }
    }

    public fun task(name: String, type: String, init: TaskDsl.() -> Unit = {}) {
        block("task<$type>(${formatValue(name)})") {
            TaskDsl(this).apply(init)
        }
    }

    public fun afterEvaluate(init: GradleDsl.() -> Unit) {
        block("afterEvaluate") {
            init(this)
        }
    }

    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    public fun apply(plugin: String) {
        line("apply(plugin = ${formatValue(plugin)})")
    }
}

public class BuildscriptDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    public fun dependencies(init: DependencyHandlerDsl.() -> Unit) {
        block("dependencies") {
            DependencyHandlerDsl(this).apply(init)
        }
    }
}

public class PluginsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun id(pluginId: String, version: String? = null) {
        if (version != null) {
            line("id(${formatValue(pluginId)}) version ${formatValue(version)}")
        } else {
            line("id(${formatValue(pluginId)})")
        }
    }

    public fun builtin(name: String) {
        line("`$name`")
    }

    public fun javaLibrary() {
        builtin("java-library")
    }

    public fun javaPlatform() {
        builtin("java-platform")
    }

    public fun versionCatalog() {
        builtin("version-catalog")
    }

    public fun mavenPublish() {
        builtin("maven-publish")
    }

    public fun signing() {
        builtin("signing")
    }

    public fun application() {
        builtin("application")
    }

    public fun gradlePublish(version: String? = null) {
        id("com.gradle.plugin-publish", version)
    }

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

    public fun kotlinMultiplatform(version: String? = null) {
        kotlin("multiplatform", version)
    }

    public fun kotlinSerialization(version: String? = null) {
        kotlin("plugin.serialization", version)
    }

    public fun kotlinParcelize() {
        kotlin("plugin.parcelize")
    }

    public fun kotlinKapt(version: String? = null) {
        kotlin("kapt", version)
    }

    public fun androidApplication(version: String? = null) {
        id("com.android.application", version)
    }

    public fun androidLibrary(version: String? = null) {
        id("com.android.library", version)
    }
}

public fun buildGradleKts(init: BuildGradleDsl.() -> Unit): String {
    return BuildGradleDsl().apply(init).build()
}