package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

public class DependencyHandlerDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a classpath dependency
     */
    public fun classpath(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("classpath(${formatValue(dependency)})")
        } else {
            block("classpath(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds an implementation dependency
     */
    public fun implementation(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("implementation(${formatValue(dependency)})")
        } else {
            block("implementation(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a test implementation dependency
     */
    public fun testImplementation(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("testImplementation(${formatValue(dependency)})")
        } else {
            block("testImplementation(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds an API dependency
     */
    public fun api(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("api(${formatValue(dependency)})")
        } else {
            block("api(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a compile only dependency
     */
    public fun compileOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("compileOnly(${formatValue(dependency)})")
        } else {
            block("compileOnly(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a runtime only dependency
     */
    public fun runtimeOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("runtimeOnly(${formatValue(dependency)})")
        } else {
            block("runtimeOnly(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a developmentOnly dependency
     */
    public fun developmentOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("developmentOnly(${formatValue(dependency)})")
        } else {
            block("developmentOnly(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds an annotationProcessor dependency
     */
    public fun annotationProcessor(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("annotationProcessor(${formatValue(dependency)})")
        } else {
            block("annotationProcessor(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a kapt dependency
     */
    public fun kapt(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("kapt(${formatValue(dependency)})")
        } else {
            block("kapt(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a platform dependency
     */
    public fun platform(dependency: String) {
        line("implementation(platform(${formatValue(dependency)}))")
    }

    /**
     * Adds a projectDependency
     */
    public fun project(configuration: String, path: String) {
        line("$configuration(project(${formatValue(path)}))")
    }

    /**
     * Adds an implementation project dependency
     */
    public fun implementationProject(path: String) {
        project("implementation", path)
    }

    /**
     * Adds an API project dependency
     */
    public fun apiProject(path: String) {
        project("api", path)
    }

    /**
     * Adds a test implementation project dependency
     */
    public fun testImplementationProject(path: String) {
        project("testImplementation", path)
    }

    /**
     * Adds a custom configuration dependency
     */
    public fun custom(configuration: String, dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("$configuration(${formatValue(dependency)})")
        } else {
            block("$configuration(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a dependency from a variable
     */
    public fun fromVariable(configuration: String, variable: String) {
        line("$configuration($variable)")
    }
}

/**
 * Unified DSL for dependency configuration including capabilities and exclusions
 */
public class DependencyConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a capability
     */
    public fun capability(group: String, name: String, version: String) {
        block("capabilities") {
            line("requireCapability(${formatValue("$group:$name:$version")}")
        }
    }

    /**
     * Excludes module
     */
    public fun exclude(group: String, module: String? = null) {
        if (module != null) {
            line("exclude(group = ${formatValue(group)}, module = ${formatValue(module)})")
        } else {
            line("exclude(group = ${formatValue(group)})")
        }
    }
}