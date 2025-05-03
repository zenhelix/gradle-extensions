package io.github.zenhelix.gradle.test.dsl

/**
 * DSL for building settings.gradle.kts content
 */
public class SettingsGradleDsl : GradleDslImpl() {
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
    public fun repositories(init: RepositoriesDsl.() -> Unit) {
        block("repositories") {
            RepositoriesDsl(this).apply(init)
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
    public fun repositories(init: RepositoriesDsl.() -> Unit) {
        block("repositories") {
            RepositoriesDsl(this).apply(init)
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
 * DSL for repositories
 */
public class RepositoriesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds mavenCentral repository
     */
    public fun mavenCentral() {
        line("mavenCentral()")
    }

    /**
     * Adds Google repository
     */
    public fun google() {
        line("google()")
    }

    /**
     * Adds mavenLocal repository
     */
    public fun mavenLocal() {
        line("mavenLocal()")
    }

    /**
     * Adds gradlePluginPortal repository
     */
    public fun gradlePluginPortal() {
        line("gradlePluginPortal()")
    }
}

/**
 * Creates and configures settings.gradle.kts DSL
 */
public fun settingsGradleKts(init: SettingsGradleDsl.() -> Unit): String {
    return SettingsGradleDsl().apply(init).build()
}