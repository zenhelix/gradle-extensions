package io.github.zenhelix.gradle.test.dsl.task.android

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractNamedDomainObjectCollectionDsl

/**
 * DSL for Android configuration
 */
public class AndroidLibraryDsl(private val parent: GradleDsl) : GradleDsl by parent {
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
     * Sets the compileSdkPreview version
     */
    public fun compileSdkPreview(version: String) {
        line("compileSdkPreview = \"$version\"")
    }

    /**
     * Sets the resourcePrefix
     */
    public fun resourcePrefix(prefix: String) {
        line("resourcePrefix = \"$prefix\"")
    }

    /**
     * Configures defaultConfig block
     */
    public fun defaultConfig(init: AndroidLibraryDefaultConfigDsl.() -> Unit) {
        block("defaultConfig") {
            AndroidLibraryDefaultConfigDsl(this).apply(init)
        }
    }

    /**
     * Configures buildTypes block
     */
    public fun buildTypes(init: AndroidLibraryBuildTypesDsl.() -> Unit) {
        block("buildTypes") {
            AndroidLibraryBuildTypesDsl(this).apply(init)
        }
    }

    /**
     * Configures buildFeatures block
     */
    public fun buildFeatures(init: AndroidLibraryBuildFeaturesDsl.() -> Unit) {
        block("buildFeatures") {
            AndroidLibraryBuildFeaturesDsl(this).apply(init)
        }
    }

    /**
     * Configures sourceSets block
     */
    public fun sourceSets(init: AndroidSourceSetsDsl.() -> Unit) {
        block("sourceSets") {
            AndroidSourceSetsDsl(this).apply(init)
        }
    }

    /**
     * Configures compileOptions block
     */
    public fun compileOptions(init: AndroidCompileOptionsDsl.() -> Unit) {
        block("compileOptions") {
            AndroidCompileOptionsDsl(this).apply(init)
        }
    }

    /**
     * Configures packaging block
     */
    public fun packaging(init: AndroidPackagingDsl.() -> Unit) {
        block("packaging") {
            AndroidPackagingDsl(this).apply(init)
        }
    }

    /**
     * Configures testOptions block
     */
    public fun testOptions(init: AndroidTestOptionsDsl.() -> Unit) {
        block("testOptions") {
            AndroidTestOptionsDsl(this).apply(init)
        }
    }

    /**
     * Configures flavorDimensions
     */
    public fun flavorDimensions(vararg dimensions: String) {
        val dimensionsStr = dimensions.joinToString("\", \"", "\"", "\"")
        line("flavorDimensions += listOf($dimensionsStr)")
    }

    /**
     * Configures productFlavors block
     */
    public fun productFlavors(init: AndroidLibraryProductFlavorsDsl.() -> Unit) {
        block("productFlavors") {
            AndroidLibraryProductFlavorsDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Android buildFeatures
 */
public class AndroidLibraryBuildFeaturesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Enables/disables viewBinding feature
     */
    public fun viewBinding(enabled: Boolean) {
        line("viewBinding = $enabled")
    }

    /**
     * Enables/disables dataBinding feature
     */
    public fun dataBinding(enabled: Boolean) {
        line("dataBinding = $enabled")
    }

    /**
     * Enables/disables compose feature
     */
    public fun compose(enabled: Boolean) {
        line("compose = $enabled")
    }

    /**
     * Enables/disables buildConfig feature
     */
    public fun buildConfig(enabled: Boolean) {
        line("buildConfig = $enabled")
    }

    /**
     * Enables/disables aidl feature
     */
    public fun aidl(enabled: Boolean) {
        line("aidl = $enabled")
    }

    /**
     * Enables/disables renderScript feature
     */
    public fun renderScript(enabled: Boolean) {
        line("renderScript = $enabled")
    }

    /**
     * Enables/disables resValues feature
     */
    public fun resValues(enabled: Boolean) {
        line("resValues = $enabled")
    }

    /**
     * Enables/disables shaders feature
     */
    public fun shaders(enabled: Boolean) {
        line("shaders = $enabled")
    }
}

/**
 * DSL for Android defaultConfig
 */
public class AndroidLibraryDefaultConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the minimum SDK version
     */
    public fun minSdk(version: Int) {
        line("minSdk = $version")
    }

    /**
     * Sets the target SDK version
     */
    @Deprecated("deprecated in android plugins")
    public fun targetSdk(version: Int) {
        line("targetSdk = $version")
    }

    /**
     * Sets testInstrumentationRunner
     */
    public fun testInstrumentationRunner(runner: String) {
        line("testInstrumentationRunner = \"$runner\"")
    }

    /**
     * Sets test functions names
     */
    public fun testFunctionalTest(value: Boolean) {
        line("testFunctionalTest = $value")
    }

    /**
     * Adds a build config field
     */
    public fun buildConfigField(type: String, name: String, value: String) {
        line("buildConfigField(\"$type\", \"$name\", \"$value\")")
    }

    /**
     * Adds a res value
     */
    public fun resValue(type: String, name: String, value: String) {
        line("resValue(\"$type\", \"$name\", \"$value\")")
    }

    /**
     * Configures vectorDrawables options
     */
    public fun vectorDrawables(init: VectorDrawablesOptionsDsl.() -> Unit) {
        block("vectorDrawables") {
            VectorDrawablesOptionsDsl(this).apply(init)
        }
    }
}

/**
 * Marker interface for Android product flavor
 */
public interface AndroidProductFlavor

/**
 * DSL for Android productFlavors
 */
public class AndroidLibraryProductFlavorsDsl(parent: GradleDsl) :
    AbstractNamedDomainObjectCollectionDsl<AndroidProductFlavor, AndroidLibraryProductFlavorDsl>(parent) {
    /**
     * Creates the configurator for a product flavor
     */
    override fun createConfigurator(dsl: GradleDsl): AndroidLibraryProductFlavorDsl = AndroidLibraryProductFlavorDsl(dsl)

}

/**
 * DSL for Android product flavor
 */
public class AndroidLibraryProductFlavorDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the dimension
     */
    public fun dimension(value: String) {
        line("dimension = \"$value\"")
    }

    /**
     * Adds a build config field
     */
    public fun buildConfigField(type: String, name: String, value: String) {
        line("buildConfigField(\"$type\", \"$name\", \"$value\")")
    }

    /**
     * Adds a res value
     */
    public fun resValue(type: String, name: String, value: String) {
        line("resValue(\"$type\", \"$name\", \"$value\")")
    }
}

/**
 * DSL for Android buildTypes
 */
public class AndroidLibraryBuildTypesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures release build type
     */
    public fun release(init: AndroidLibraryBuildTypeDsl.() -> Unit) {
        block("release") {
            AndroidLibraryBuildTypeDsl(this).apply(init)
        }
    }

    /**
     * Configures debug build type
     */
    public fun debug(init: AndroidLibraryBuildTypeDsl.() -> Unit) {
        block("debug") {
            AndroidLibraryBuildTypeDsl(this).apply(init)
        }
    }

    /**
     * Creates a custom build type
     */
    public fun create(name: String, init: AndroidLibraryBuildTypeDsl.() -> Unit) {
        block("create(\"$name\")") {
            AndroidLibraryBuildTypeDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Android build type
 */
public class AndroidLibraryBuildTypeDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Enables/disables minification
     */
    public fun minifyEnabled(enabled: Boolean) {
        line("isMinifyEnabled = $enabled")
    }

    /**
     * Sets debuggable property
     */
    public fun debuggable(value: Boolean) {
        line("isDebuggable = $value")
    }

    /**
     * Sets jniDebuggable property
     */
    public fun jniDebuggable(value: Boolean) {
        line("isJniDebuggable = $value")
    }

    /**
     * Sets shrinkResources property
     */
    public fun shrinkResources(value: Boolean) {
        line("isShrinkResources = $value")
    }

    /**
     * Adds a build config field
     */
    public fun buildConfigField(type: String, name: String, value: String) {
        line("buildConfigField(\"$type\", \"$name\", \"$value\")")
    }

    /**
     * Adds a res value
     */
    public fun resValue(type: String, name: String, value: String) {
        line("resValue(\"$type\", \"$name\", \"$value\")")
    }

    /**
     * Sets matchingFallbacks
     */
    public fun matchingFallbacks(vararg fallbacks: String) {
        val fallbacksStr = fallbacks.joinToString("\", \"", "\"", "\"")
        line("matchingFallbacks += listOf($fallbacksStr)")
    }
}