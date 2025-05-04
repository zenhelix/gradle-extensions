package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.gradle.RepositoryHandlerDsl

/**
 * DSL for building settings.gradle.kts content
 */
public class SettingsGradleDsl : GradleDslImpl() {
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
     * Sets the root project name
     */
    public fun rootProjectName(name: String) {
        line("rootProject.name = \"$name\"")
        line("")
    }

    /**
     * Adds the specified modules to the project
     */
    public fun include(vararg modules: String) {
        val modulesList = modules.joinToString(",\n    ") { "\":$it\"" }
        line("include(")
        line("    $modulesList")
        line(")")
        line("")
    }

    /**
     * Adds dependencyResolutionManagement block
     */
    public fun dependencyResolutionManagement(init: DependencyManagementDsl.() -> Unit) {
        block("dependencyResolutionManagement") {
            DependencyManagementDsl(this).apply(init)
        }
        line("")
    }

    /**
     * Adds pluginManagement block
     */
    public fun pluginManagement(init: PluginManagementDsl.() -> Unit) {
        block("pluginManagement") {
            PluginManagementDsl(this).apply(init)
        }
        line("")
    }
}

/**
 * DSL for dependency management
 */
public class DependencyManagementDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures repositories for dependencies
     */
    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }
}

/**
 * DSL for plugin management
 */
public class PluginManagementDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures repositories for plugins
     */
    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    /**
     * Configures plugins
     */
    public fun plugins(init: PluginsSettingsDsl.() -> Unit) {
        block("plugins") {
            PluginsSettingsDsl(this).apply(init)
        }
    }
}

/**
 * DSL for plugin settings in pluginManagement
 */
public class PluginsSettingsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a plugin with specified version
     */
    public fun id(pluginId: String, version: String) {
        line("id(\"$pluginId\") version \"$version\"")
    }
}

/**
 * Creates and configures settings.gradle.kts DSL
 */
public fun settingsGradleKts(init: SettingsGradleDsl.() -> Unit): String {
    return SettingsGradleDsl().apply(init).build()
}