package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * DSL for Android configuration
 */
public class AndroidDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the namespace
     */
    public fun namespace(value: String) {
        line("namespace = \"$value\"")
    }

    /**
     * Sets the compileSdk version
     */
    public fun compileSdk(version: Int) {
        line("compileSdk = $version")
    }

    /**
     * Configures defaultConfig block
     */
    public fun defaultConfig(init: AndroidDefaultConfigDsl.() -> Unit) {
        block("defaultConfig") {
            AndroidDefaultConfigDsl(this).apply(init)
        }
    }

    /**
     * Configures buildTypes block
     */
    public fun buildTypes(init: AndroidBuildTypesDsl.() -> Unit) {
        block("buildTypes") {
            AndroidBuildTypesDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Android defaultConfig
 */
public class AndroidDefaultConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the minimum SDK version
     */
    public fun minSdk(version: Int) {
        line("minSdk = $version")
    }

    /**
     * Sets the target SDK version
     */
    public fun targetSdk(version: Int) {
        line("targetSdk = $version")
    }

    /**
     * Sets the version code
     */
    public fun versionCode(code: Int) {
        line("versionCode = $code")
    }

    /**
     * Sets the version name
     */
    public fun versionName(name: String) {
        line("versionName = \"$name\"")
    }
}

/**
 * DSL for Android buildTypes
 */
public class AndroidBuildTypesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures release build type
     */
    public fun release(init: AndroidBuildTypeDsl.() -> Unit) {
        block("release") {
            AndroidBuildTypeDsl(this).apply(init)
        }
    }

    /**
     * Configures debug build type
     */
    public fun debug(init: AndroidBuildTypeDsl.() -> Unit) {
        block("debug") {
            AndroidBuildTypeDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Android build type
 */
public class AndroidBuildTypeDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Enables/disables minification
     */
    public fun minifyEnabled(enabled: Boolean) {
        line("minifyEnabled = $enabled")
    }

    /**
     * Sets proguard files
     */
    public fun proguardFiles(vararg files: String) {
        val filesStr = files.joinToString(", ") { "getDefaultProguardFile(\"$it\")" }
        line("proguardFiles($filesStr)")
    }
}