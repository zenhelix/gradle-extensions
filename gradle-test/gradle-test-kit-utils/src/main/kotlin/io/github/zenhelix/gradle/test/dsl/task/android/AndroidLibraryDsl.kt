package io.github.zenhelix.gradle.test.dsl.task.android

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.PropertyDelegate
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractNamedDomainObjectCollectionDsl

/**
 * DSL for Android configuration
 */
public class AndroidLibraryDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the namespace
     */
    @Deprecated("Use property assignment instead", ReplaceWith("namespace = value"))
    public fun namespace(value: String) {
        line("namespace = \"$value\"")
    }

    /**
     * Direct property assignment for namespace
     */
    public var namespace: String by PropertyDelegate(parent)

    /**
     * Sets the compileSdk version
     */
    @Deprecated("Use property assignment instead", ReplaceWith("compileSdk = version"))
    public fun compileSdk(version: Int) {
        line("compileSdk = $version")
    }

    /**
     * Direct property assignment for compileSdk
     */
    public var compileSdk: Int by PropertyDelegate(parent)

    /**
     * Sets the compileSdkPreview version
     */
    @Deprecated("Use property assignment instead", ReplaceWith("compileSdkPreview = version"))
    public fun compileSdkPreview(version: String) {
        line("compileSdkPreview = \"$version\"")
    }

    /**
     * Direct property assignment for compileSdkPreview
     */
    public var compileSdkPreview: String by PropertyDelegate(parent)

    /**
     * Sets the resourcePrefix
     */
    @Deprecated("Use property assignment instead", ReplaceWith("resourcePrefix = prefix"))
    public fun resourcePrefix(prefix: String) {
        line("resourcePrefix = \"$prefix\"")
    }

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
     * Enables/disables viewBinding feature
     */
    @Deprecated("Use property assignment instead", ReplaceWith("viewBinding = enabled"))
    public fun viewBinding(enabled: Boolean) {
        line("viewBinding = $enabled")
    }

    /**
     * Direct property assignment for viewBinding
     */
    public var viewBinding: Boolean by PropertyDelegate(parent)

    /**
     * Enables/disables dataBinding feature
     */
    @Deprecated("Use property assignment instead", ReplaceWith("dataBinding = enabled"))
    public fun dataBinding(enabled: Boolean) {
        line("dataBinding = $enabled")
    }

    /**
     * Direct property assignment for dataBinding
     */
    public var dataBinding: Boolean by PropertyDelegate(parent)

    /**
     * Enables/disables compose feature
     */
    @Deprecated("Use property assignment instead", ReplaceWith("compose = enabled"))
    public fun compose(enabled: Boolean) {
        line("compose = $enabled")
    }

    /**
     * Direct property assignment for compose
     */
    public var compose: Boolean by PropertyDelegate(parent)

    /**
     * Enables/disables buildConfig feature
     */
    @Deprecated("Use property assignment instead", ReplaceWith("buildConfig = enabled"))
    public fun buildConfig(enabled: Boolean) {
        line("buildConfig = $enabled")
    }

    /**
     * Direct property assignment for buildConfig
     */
    public var buildConfig: Boolean by PropertyDelegate(parent)

    /**
     * Enables/disables aidl feature
     */
    @Deprecated("Use property assignment instead", ReplaceWith("aidl = enabled"))
    public fun aidl(enabled: Boolean) {
        line("aidl = $enabled")
    }

    /**
     * Direct property assignment for aidl
     */
    public var aidl: Boolean by PropertyDelegate(parent)

    /**
     * Enables/disables renderScript feature
     */
    @Deprecated("Use property assignment instead", ReplaceWith("renderScript = enabled"))
    public fun renderScript(enabled: Boolean) {
        line("renderScript = $enabled")
    }

    /**
     * Direct property assignment for renderScript
     */
    public var renderScript: Boolean by PropertyDelegate(parent)

    /**
     * Enables/disables resValues feature
     */
    @Deprecated("Use property assignment instead", ReplaceWith("resValues = enabled"))
    public fun resValues(enabled: Boolean) {
        line("resValues = $enabled")
    }

    /**
     * Direct property assignment for resValues
     */
    public var resValues: Boolean by PropertyDelegate(parent)

    /**
     * Enables/disables shaders feature
     */
    @Deprecated("Use property assignment instead", ReplaceWith("shaders = enabled"))
    public fun shaders(enabled: Boolean) {
        line("shaders = $enabled")
    }

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
     * Sets the minimum SDK version
     */
    @Deprecated("Use property assignment instead", ReplaceWith("minSdk = version"))
    public fun minSdk(version: Int) {
        line("minSdk = $version")
    }

    /**
     * Direct property assignment for minSdk
     */
    public var minSdk: Int by PropertyDelegate(parent)

    /**
     * Sets the target SDK version
     */
    @Deprecated("deprecated in android plugins", ReplaceWith("targetSdk = version"))
    public fun targetSdk(version: Int) {
        line("targetSdk = $version")
    }

    /**
     * Direct property assignment for targetSdk
     */
    @Deprecated("deprecated in android plugins")
    public var targetSdk: Int by PropertyDelegate(parent)

    /**
     * Sets testInstrumentationRunner
     */
    @Deprecated("Use property assignment instead", ReplaceWith("testInstrumentationRunner = runner"))
    public fun testInstrumentationRunner(runner: String) {
        line("testInstrumentationRunner = \"$runner\"")
    }

    /**
     * Direct property assignment for testInstrumentationRunner
     */
    public var testInstrumentationRunner: String by PropertyDelegate(parent)

    /**
     * Sets test functions names
     */
    @Deprecated("Use property assignment instead", ReplaceWith("testFunctionalTest = value"))
    public fun testFunctionalTest(value: Boolean) {
        line("testFunctionalTest = $value")
    }

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