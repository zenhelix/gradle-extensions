package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

public class  DependencyHandlerDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a classpath dependency
     */
    public fun classpath(dependency: String) {
        line("classpath(\"$dependency\")")
    }

    /**
     * Adds an implementation dependency
     */
    public fun implementation(dependency: String) {
        line("implementation(\"$dependency\")")
    }

    /**
     * Adds a test implementation dependency
     */
    public fun testImplementation(dependency: String) {
        line("testImplementation(\"$dependency\")")
    }

    /**
     * Adds an API dependency
     */
    public fun api(dependency: String) {
        line("api(\"$dependency\")")
    }

    /**
     * Adds a compile only dependency
     */
    public fun compileOnly(dependency: String) {
        line("compileOnly(\"$dependency\")")
    }

    /**
     * Adds a runtime only dependency
     */
    public fun runtimeOnly(dependency: String) {
        line("runtimeOnly(\"$dependency\")")
    }

    /**
     * Adds a developmentOnly dependency
     */
    public fun developmentOnly(dependency: String) {
        line("developmentOnly(\"$dependency\")")
    }

    /**
     * Adds an annotationProcessor dependency
     */
    public fun annotationProcessor(dependency: String) {
        line("annotationProcessor(\"$dependency\")")
    }

    /**
     * Adds a kapt dependency
     */
    public fun kapt(dependency: String) {
        line("kapt(\"$dependency\")")
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
     * Adds a implementation project dependency
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
    public fun custom(configuration: String, dependency: String) {
        line("$configuration(\"$dependency\")")
    }

    /**
     * Adds a dependency from a variable
     */
    public fun fromVariable(configuration: String, variable: String) {
        line("$configuration($variable)")
    }

    /**
     * Adds a dependency with an exclude
     */
    public fun implementationWithExclude(dependency: String, group: String, module: String? = null) {
        if (module != null) {
            line("implementation(\"$dependency\") {")
            line("    exclude(group = \"$group\", module = \"$module\")")
            line("}")
        } else {
            line("implementation(\"$dependency\") {")
            line("    exclude(group = \"$group\")")
            line("}")
        }
    }

    /**
     * Adds a dependency with capabilities
     */
    public fun implementationWithCapabilities(dependency: String, init: CapabilitiesDsl.() -> Unit) {
        block("implementation(\"$dependency\")") {
            CapabilitiesDsl(this).apply(init)
        }
    }

}


/**
 * DSL for capabilities
 */
public class CapabilitiesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a capability
     */
    public fun capability(group: String, name: String, version: String) {
        line("capabilities {")
        line("    requireCapability(\"$group:$name:$version\")")
        line("}")
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
