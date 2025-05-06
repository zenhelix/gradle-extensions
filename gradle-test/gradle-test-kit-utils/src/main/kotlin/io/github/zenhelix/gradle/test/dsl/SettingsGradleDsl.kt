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
        line("rootProject.name = ${formatValue(name)}")
        line("")
    }

    /**
     * Adds the specified modules to the project
     */
    public fun include(vararg modules: String) {
        if (modules.size == 1) {
            line("include(\":${modules[0]}\")")
        } else {
            val modulesList = modules.joinToString(",\n    ") { "\":$it\"" }
            line("include(")
            line("    $modulesList")
            line(")")
        }
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

    /**
     * Configures version catalogs
     */
    public fun versionCatalogs(init: VersionCatalogsDsl.() -> Unit) {
        block("versionCatalogs") {
            VersionCatalogsDsl(this).apply(init)
        }
    }
}

/**
 * DSL for version catalogs
 */
public class VersionCatalogsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Creates a catalog
     */
    public fun create(name: String, init: VersionCatalogDsl.() -> Unit) {
        block("create(${formatValue(name)})") {
            VersionCatalogDsl(this).apply(init)
        }
    }
}

/**
 * DSL for version catalog configuration
 */
public class VersionCatalogDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a version
     */
    public fun version(alias: String, version: String) {
        line("version(${formatValue(alias)}, ${formatValue(version)})")
    }

    /**
     * Adds a library
     */
    public fun library(alias: String, group: String, name: String, version: String) {
        line("library(${formatValue(alias)}, ${formatValue(group)}, ${formatValue(name)}, ${formatValue(version)})")
    }

    /**
     * Adds a plugin
     */
    public fun plugin(alias: String, id: String, version: String) {
        line("plugin(${formatValue(alias)}, ${formatValue(id)}, ${formatValue(version)})")
    }

    /**
     * Creates a bundle
     */
    public fun bundle(alias: String, vararg libraries: String) {
        val libsStr = libraries.joinToString("\", \"", "\"", "\"")
        line("bundle(${formatValue(alias)}, listOf($libsStr))")
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
        line("id(${formatValue(pluginId)}) version ${formatValue(version)}")
    }

}

/**
 * Creates and configures settings.gradle.kts DSL
 */
public fun settingsGradleKts(init: SettingsGradleDsl.() -> Unit): String {
    return SettingsGradleDsl().apply(init).build()
}