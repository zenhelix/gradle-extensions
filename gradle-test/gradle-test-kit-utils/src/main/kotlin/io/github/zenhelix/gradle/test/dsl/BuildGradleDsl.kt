package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.task.AndroidDsl
import io.github.zenhelix.gradle.test.dsl.task.KotlinMultiplatformDsl
import io.github.zenhelix.gradle.test.dsl.task.PomDsl
import io.github.zenhelix.gradle.test.dsl.task.PublishingDsl
import io.github.zenhelix.gradle.test.dsl.task.SigningDsl
import io.github.zenhelix.gradle.test.dsl.task.TaskDsl

/**
 * DSL for building build.gradle.kts content
 */
public class BuildGradleDsl : GradleDslImpl() {
    /**
     * Adds an import statement
     */
    public fun import(path: String) {
        line("import $path")
    }

    /**
     * Configures plugins block
     */
    public fun plugins(init: PluginsDsl.() -> Unit) {
        block("plugins") {
            PluginsDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Configures allprojects block
     */
    public fun allprojects(init: ProjectConfigDsl.() -> Unit) {
        block("allprojects") {
            ProjectConfigDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Configures subprojects block
     */
    public fun subprojects(init: ProjectConfigDsl.() -> Unit) {
        block("subprojects") {
            ProjectConfigDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Creates a configure block with filter
     */
    public fun configure(filter: String, init: ProjectConfigDsl.() -> Unit) {
        block("configure($filter)") {
            ProjectConfigDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Adds a variable
     */
    public fun variable(name: String, type: String, value: String) {
        line("val $name: $type = $value")
        line("")
    }

    /**
     * Configures publishing block
     */
    public fun publishing(init: PublishingDsl.() -> Unit) {
        block("publishing") {
            PublishingDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Configures signing block
     */
    public fun signing(init: SigningDsl.() -> Unit) {
        block("signing") {
            SigningDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Configures MavenPublication publications
     */
    public fun withTypeMavenPublication(init: PomDsl.() -> Unit) {
        block("publishing.publications.withType<MavenPublication>") {
            PomDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Configures kotlin block for multiplatform
     */
    public fun kotlin(init: KotlinMultiplatformDsl.() -> Unit) {
        block("kotlin") {
            KotlinMultiplatformDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Configures android block
     */
    public fun android(init: AndroidDsl.() -> Unit) {
        block("android") {
            AndroidDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Registers a task
     */
    public fun task(name: String, init: TaskDsl.() -> Unit) {
        block("tasks.register(\"$name\")") {
            TaskDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Configures repositories block
     */
    public fun repositories(init: RepositoriesDsl.() -> Unit) {
        block("repositories") {
            RepositoriesDsl(this).init()
        }
        line("")
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
            line("id(\"$pluginId\") version \"$version\"")
        } else {
            line("id(\"$pluginId\")")
        }
    }

    /**
     * Adds a built-in plugin
     */
    public fun builtin(name: String) {
        line("`$name`")
    }

    /**
     * Adds kotlin plugin
     */
    public fun kotlin(version: String? = null) {
        id("org.jetbrains.kotlin.jvm", version)
    }

    /**
     * Adds kotlin multiplatform plugin
     */
    public fun kotlinMultiplatform(version: String? = null) {
        id("org.jetbrains.kotlin.multiplatform", version)
    }

    /**
     * Adds android library plugin
     */
    public fun androidLibrary(version: String? = null) {
        id("com.android.library", version)
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
}

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
     * Configures apply block
     */
    public fun apply(init: ApplyDsl.() -> Unit) {
        block("apply") {
            ApplyDsl(this).apply(init)
        }
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

    /**
     * Applies a built-in plugin
     */
    public fun builtin(name: String) {
        line("plugin(`$name`)")
    }
}

/**
 * Creates and configures build.gradle.kts DSL
 */
public fun buildGradleKts(init: BuildGradleDsl.() -> Unit): String {
    return BuildGradleDsl().apply(init).build()
}