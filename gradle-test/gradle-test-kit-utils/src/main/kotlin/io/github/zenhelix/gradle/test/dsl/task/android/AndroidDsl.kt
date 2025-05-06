package io.github.zenhelix.gradle.test.dsl.task.android

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate
import org.gradle.api.JavaVersion

/**
 * DSL for vectorDrawables options
 */
public class VectorDrawablesOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Direct property assignment for useSupportLibrary
     */
    public var useSupportLibrary: Boolean by PropertyDelegate(parent)
}

/**
 * DSL for Android sourceSets
 */
public class AndroidSourceSetsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures main source set
     */
    public fun main(init: AndroidSourceSetDsl.() -> Unit) {
        block("named(\"main\")") {
            AndroidSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures test source set
     */
    public fun test(init: AndroidSourceSetDsl.() -> Unit) {
        block("named(\"test\")") {
            AndroidSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures androidTest source set
     */
    public fun androidTest(init: AndroidSourceSetDsl.() -> Unit) {
        block("named(\"androidTest\")") {
            AndroidSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures named source set
     */
    public fun named(name: String, init: AndroidSourceSetDsl.() -> Unit) {
        block("named(${formatValue(name)})") {
            AndroidSourceSetDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Android source set
 */
public class AndroidSourceSetDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets java.srcDirs property
     */
    public fun java(vararg dirs: String) {
        val dirsStr = dirs.joinToString("\", \"", "\"", "\"")
        line("java.setSrcDirs(listOf($dirsStr))")
    }

    /**
     * Sets res.srcDirs property
     */
    public fun res(vararg dirs: String) {
        val dirsStr = dirs.joinToString("\", \"", "\"", "\"")
        line("res.setSrcDirs(listOf($dirsStr))")
    }

    /**
     * Sets assets.srcDirs property
     */
    public fun assets(vararg dirs: String) {
        val dirsStr = dirs.joinToString("\", \"", "\"", "\"")
        line("assets.setSrcDirs(listOf($dirsStr))")
    }

    /**
     * Sets manifest.srcFile property
     */
    public fun manifest(file: String) {
        line("manifest.srcFile(${formatValue(file)})")
    }
}

/**
 * DSL for Android compileOptions
 */
public class AndroidCompileOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Direct property assignment for sourceCompatibility
     */
    public var sourceCompatibility: JavaVersion by PropertyDelegate(parent) { "JavaVersion.${it.name}" }

    /**
     * Direct property assignment for targetCompatibility
     */
    public var targetCompatibility: JavaVersion by PropertyDelegate(parent) { "JavaVersion.${it.name}" }

    /**
     * Direct property assignment for isCoreLibraryDesugaringEnabled
     */
    public var isCoreLibraryDesugaringEnabled: Boolean by PropertyDelegate(parent)
}

/**
 * DSL for Android packaging
 */
public class AndroidPackagingDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures resources for packaging
     */
    public fun resources(init: AndroidPackagingResourcesDsl.() -> Unit) {
        block("resources") {
            AndroidPackagingResourcesDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Android packaging resources
 */
public class AndroidPackagingResourcesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Excludes resources from packaging
     */
    public fun excludes(vararg patterns: String) {
        val patternsStr = patterns.joinToString("\", \"", "\"", "\"")
        line("excludes += setOf($patternsStr)")
    }

    public fun pickFirsts(vararg patterns: String) {
        val patternsStr = patterns.joinToString("\", \"", "\"", "\"")
        line("pickFirsts += setOf($patternsStr)")
    }

    public fun merges(vararg patterns: String) {
        val patternsStr = patterns.joinToString("\", \"", "\"", "\"")
        line("merges += setOf($patternsStr)")
    }
}

/**
 * DSL for Android testOptions
 */
public class AndroidTestOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures unitTests block
     */
    public fun unitTests(init: AndroidUnitTestsDsl.() -> Unit) {
        block("unitTests") {
            AndroidUnitTestsDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Android unitTests
 */
public class AndroidUnitTestsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Direct property assignment for isIncludeAndroidResources
     */
    public var isIncludeAndroidResources: Boolean by PropertyDelegate(parent)

    /**
     * Direct property assignment for isReturnDefaultValues
     */
    public var isReturnDefaultValues: Boolean by PropertyDelegate(parent)
}