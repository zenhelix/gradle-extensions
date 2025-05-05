package io.github.zenhelix.gradle.test.dsl.task.android

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.PropertyDelegate
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractNamedDomainObjectCollectionDsl

/**
 * DSL for Android configuration
 */
public class AndroidLibraryDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Direct property assignment for namespace
     */
    public var namespace: String by PropertyDelegate(parent)

    /**
     * Direct property assignment for compileSdk
     */
    public var compileSdk: Int by PropertyDelegate(parent)

    /**
     * Direct property assignment for compileSdkPreview
     */
    public var compileSdkPreview: String by PropertyDelegate(parent)

    /**
     * Direct property assignment for resourcePrefix
     */
    public var resourcePrefix: String by PropertyDelegate(parent)

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
     * Direct property assignment for viewBinding
     */
    public var viewBinding: Boolean by PropertyDelegate(parent)

    /**
     * Direct property assignment for dataBinding
     */
    public var dataBinding: Boolean by PropertyDelegate(parent)

    /**
     * Direct property assignment for compose
     */
    public var compose: Boolean by PropertyDelegate(parent)

    /**
     * Direct property assignment for buildConfig
     */
    public var buildConfig: Boolean by PropertyDelegate(parent)

    /**
     * Direct property assignment for aidl
     */
    public var aidl: Boolean by PropertyDelegate(parent)

    /**
     * Direct property assignment for renderScript
     */
    public var renderScript: Boolean by PropertyDelegate(parent)

    /**
     * Direct property assignment for resValues
     */
    public var resValues: Boolean by PropertyDelegate(parent)

    /**
     * Direct property assignment for shaders
     */
    public var shaders: Boolean by PropertyDelegate(parent)
}

/**
 * DSL for Android defaultConfig
 */
public class AndroidLibraryDefaultConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Direct property assignment for minSdk
     */
    public var minSdk: Int by PropertyDelegate(parent)

    /**
     * Direct property assignment for targetSdk
     */
    @Deprecated("deprecated in android plugins")
    public var targetSdk: Int by PropertyDelegate(parent)

    /**
     * Direct property assignment for testInstrumentationRunner
     */
    public var testInstrumentationRunner: String by PropertyDelegate(parent)

    /**
     * Direct property assignment for testFunctionalTest
     */
    public var testFunctionalTest: Boolean by PropertyDelegate(parent)

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