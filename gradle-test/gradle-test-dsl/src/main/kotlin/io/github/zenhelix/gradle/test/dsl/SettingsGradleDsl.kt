package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.gradle.RepositoryHandlerDsl

public class SettingsGradleDsl : GradleDslImpl() {

    public fun import(vararg paths: String) {
        paths.forEach { path ->
            line("import $path")
        }
    }

    public fun rootProjectName(name: String) {
        line("rootProject.name = ${formatValue(name)}")
    }

    public fun include(vararg modules: String) {
        if (modules.size == 1) {
            line("include(\":${modules[0]}\")")
        } else {
            val modulesList = modules.joinToString(",\n    ") { "\":$it\"" }
            line("include(")
            line("    $modulesList")
            line(")")
        }
    }

    public fun dependencyResolutionManagement(init: DependencyManagementDsl.() -> Unit) {
        block("dependencyResolutionManagement") {
            DependencyManagementDsl(this).apply(init)
        }
    }

    public fun pluginManagement(init: PluginManagementDsl.() -> Unit) {
        block("pluginManagement") {
            PluginManagementDsl(this).apply(init)
        }
    }
}

public class DependencyManagementDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    public fun versionCatalogs(init: VersionCatalogsDsl.() -> Unit) {
        block("versionCatalogs") {
            VersionCatalogsDsl(this).apply(init)
        }
    }
}

public class VersionCatalogsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun create(name: String, init: VersionCatalogDsl.() -> Unit) {
        block("create(${formatValue(name)})") {
            VersionCatalogDsl(this).apply(init)
        }
    }
}

public class VersionCatalogDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun version(alias: String, version: String) {
        line("version(${formatValue(alias)}, ${formatValue(version)})")
    }

    public fun library(alias: String, group: String, name: String, version: String) {
        line("library(${formatValue(alias)}, ${formatValue(group)}, ${formatValue(name)}, ${formatValue(version)})")
    }

    public fun plugin(alias: String, id: String, version: String) {
        line("plugin(${formatValue(alias)}, ${formatValue(id)}, ${formatValue(version)})")
    }

    public fun bundle(alias: String, vararg libraries: String) {
        val libsStr = libraries.joinToString("\", \"", "\"", "\"")
        line("bundle(${formatValue(alias)}, listOf($libsStr))")
    }
}

public class PluginManagementDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    public fun plugins(init: PluginsSettingsDsl.() -> Unit) {
        block("plugins") {
            PluginsSettingsDsl(this).apply(init)
        }
    }
}

public class PluginsSettingsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun id(pluginId: String, version: String) {
        line("id(${formatValue(pluginId)}) version ${formatValue(version)}")
    }

}

public fun settingsGradleKts(init: SettingsGradleDsl.() -> Unit): String {
    return SettingsGradleDsl().apply(init).build()
}