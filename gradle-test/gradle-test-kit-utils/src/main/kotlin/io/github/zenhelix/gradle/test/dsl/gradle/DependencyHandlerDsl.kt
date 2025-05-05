package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

public class DependencyHandlerDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a classpath dependency
     */
    public fun classpath(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("classpath(\"$dependency\")")
        } else {
            block("classpath(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds an implementation dependency
     */
    public fun implementation(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("implementation(\"$dependency\")")
        } else {
            block("implementation(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a test implementation dependency
     */
    public fun testImplementation(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("testImplementation(\"$dependency\")")
        } else {
            block("testImplementation(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds an API dependency
     */
    public fun api(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("api(\"$dependency\")")
        } else {
            block("api(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a compile only dependency
     */
    public fun compileOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("compileOnly(\"$dependency\")")
        } else {
            block("compileOnly(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a runtime only dependency
     */
    public fun runtimeOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("runtimeOnly(\"$dependency\")")
        } else {
            block("runtimeOnly(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a developmentOnly dependency
     */
    public fun developmentOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("developmentOnly(\"$dependency\")")
        } else {
            block("developmentOnly(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds an annotationProcessor dependency
     */
    public fun annotationProcessor(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("annotationProcessor(\"$dependency\")")
        } else {
            block("annotationProcessor(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a kapt dependency
     */
    public fun kapt(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("kapt(\"$dependency\")")
        } else {
            block("kapt(\"$dependency\")") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds a platform dependency
     */
    public fun platform(dependency: String) {
        line("implementation(platform(\"$dependency\"))")
    }

    /**
     * Adds a projectDependency
     */
    public fun project(configuration: String, path: String) {
        line("$configuration(project(\"$path\"))")
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
            line("$configuration(\"$dependency\")")
        } else {
            block("$configuration(\"$dependency\")") {
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
            line("requireCapability(\"$group:$name:$version\")")
        }
    }

    /**
     * Excludes module
     */
    public fun exclude(group: String, module: String? = null) {
        if (module != null) {
            line("exclude(group = \"$group\", module = \"$module\")")
        } else {
            line("exclude(group = \"$group\")")
        }
    }
}