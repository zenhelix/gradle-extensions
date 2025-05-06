package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

public class DependencyHandlerDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun classpath(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("classpath(${formatValue(dependency)})")
        } else {
            block("classpath(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun implementation(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("implementation(${formatValue(dependency)})")
        } else {
            block("implementation(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun testImplementation(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("testImplementation(${formatValue(dependency)})")
        } else {
            block("testImplementation(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun api(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("api(${formatValue(dependency)})")
        } else {
            block("api(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun compileOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("compileOnly(${formatValue(dependency)})")
        } else {
            block("compileOnly(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun runtimeOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("runtimeOnly(${formatValue(dependency)})")
        } else {
            block("runtimeOnly(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun developmentOnly(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("developmentOnly(${formatValue(dependency)})")
        } else {
            block("developmentOnly(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun annotationProcessor(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("annotationProcessor(${formatValue(dependency)})")
        } else {
            block("annotationProcessor(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun kapt(dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("kapt(${formatValue(dependency)})")
        } else {
            block("kapt(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun platform(dependency: String) {
        line("implementation(platform(${formatValue(dependency)}))")
    }

    public fun project(configuration: String, path: String) {
        line("$configuration(project(${formatValue(path)}))")
    }

    public fun implementationProject(path: String) {
        project("implementation", path)
    }

    public fun apiProject(path: String) {
        project("api", path)
    }

    public fun testImplementationProject(path: String) {
        project("testImplementation", path)
    }

    public fun custom(configuration: String, dependency: String, init: DependencyConfigDsl.() -> Unit = {}) {
        if (init == {}) {
            line("$configuration(${formatValue(dependency)})")
        } else {
            block("$configuration(${formatValue(dependency)})") {
                DependencyConfigDsl(this).apply(init)
            }
        }
    }

    public fun fromVariable(configuration: String, variable: String) {
        line("$configuration($variable)")
    }
}

public class DependencyConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun capability(group: String, name: String, version: String) {
        block("capabilities") {
            line("requireCapability(${formatValue("$group:$name:$version")}")
        }
    }

    public fun exclude(group: String, module: String? = null) {
        if (module != null) {
            line("exclude(group = ${formatValue(group)}, module = ${formatValue(module)})")
        } else {
            line("exclude(group = ${formatValue(group)})")
        }
    }
}