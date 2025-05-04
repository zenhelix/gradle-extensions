package io.github.zenhelix.gradle.test.dsl.task.android

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import org.gradle.api.JavaVersion

/**
 * DSL for vectorDrawables options
 */
public class VectorDrawablesOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets useSupportLibrary property
     */
    public fun useSupportLibrary(value: Boolean) {
        line("useSupportLibrary = $value")
    }
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
        block("named(\"$name\")") {
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
        line("manifest.srcFile(\"$file\")")
    }
}


/**
 * DSL for Android compileOptions
 */
public class AndroidCompileOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the source compatibility
     */
    public fun sourceCompatibility(version: JavaVersion) {
        line("sourceCompatibility = JavaVersion.${version.name}")
    }

    /**
     * Sets the target compatibility
     */
    public fun targetCompatibility(version: JavaVersion) {
        line("targetCompatibility = JavaVersion.${version.name}")
    }

    /**
     * Enables/disables core library desugaring
     */
    public fun isCoreLibraryDesugaringEnabled(enabled: Boolean) {
        line("isCoreLibraryDesugaringEnabled = $enabled")
    }
}


/**
 * DSL for Android packaging
 */
public class AndroidPackagingDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures resources to exclude
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
     * Sets isIncludeAndroidResources property
     */
    public fun isIncludeAndroidResources(include: Boolean) {
        line("isIncludeAndroidResources = $include")
    }

    /**
     * Sets isReturnDefaultValues property
     */
    public fun isReturnDefaultValues(returnDefaults: Boolean) {
        line("isReturnDefaultValues = $returnDefaults")
    }
}