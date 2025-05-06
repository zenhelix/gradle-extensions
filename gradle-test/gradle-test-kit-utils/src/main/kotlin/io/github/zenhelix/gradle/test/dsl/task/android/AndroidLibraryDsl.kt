package io.github.zenhelix.gradle.test.dsl.task.android

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractNamedDomainObjectCollectionDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate

public class AndroidLibraryDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var namespace: String by PropertyDelegate(parent)

    public var compileSdk: Int by PropertyDelegate(parent)

    public var compileSdkPreview: String by PropertyDelegate(parent)

    public var resourcePrefix: String by PropertyDelegate(parent)

    public fun defaultConfig(init: AndroidLibraryDefaultConfigDsl.() -> Unit) {
        block("defaultConfig") {
            AndroidLibraryDefaultConfigDsl(this).apply(init)
        }
    }

    public fun buildTypes(init: AndroidLibraryBuildTypesDsl.() -> Unit) {
        block("buildTypes") {
            AndroidLibraryBuildTypesDsl(this).apply(init)
        }
    }

    public fun buildFeatures(init: AndroidLibraryBuildFeaturesDsl.() -> Unit) {
        block("buildFeatures") {
            AndroidLibraryBuildFeaturesDsl(this).apply(init)
        }
    }

    public fun sourceSets(init: AndroidSourceSetsDsl.() -> Unit) {
        block("sourceSets") {
            AndroidSourceSetsDsl(this).apply(init)
        }
    }

    public fun compileOptions(init: AndroidCompileOptionsDsl.() -> Unit) {
        block("compileOptions") {
            AndroidCompileOptionsDsl(this).apply(init)
        }
    }

    public fun packaging(init: AndroidPackagingDsl.() -> Unit) {
        block("packaging") {
            AndroidPackagingDsl(this).apply(init)
        }
    }

    public fun testOptions(init: AndroidTestOptionsDsl.() -> Unit) {
        block("testOptions") {
            AndroidTestOptionsDsl(this).apply(init)
        }
    }

    public fun flavorDimensions(vararg dimensions: String) {
        val dimensionsStr = dimensions.joinToString("\", \"", "\"", "\"")
        line("flavorDimensions += listOf($dimensionsStr)")
    }

    public fun productFlavors(init: AndroidLibraryProductFlavorsDsl.() -> Unit) {
        block("productFlavors") {
            AndroidLibraryProductFlavorsDsl(this).apply(init)
        }
    }
}

public class AndroidLibraryBuildFeaturesDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var viewBinding: Boolean by PropertyDelegate(parent)

    public var dataBinding: Boolean by PropertyDelegate(parent)

    public var compose: Boolean by PropertyDelegate(parent)

    public var buildConfig: Boolean by PropertyDelegate(parent)

    public var aidl: Boolean by PropertyDelegate(parent)

    public var renderScript: Boolean by PropertyDelegate(parent)

    public var resValues: Boolean by PropertyDelegate(parent)

    public var shaders: Boolean by PropertyDelegate(parent)
}

public class AndroidLibraryDefaultConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var minSdk: Int by PropertyDelegate(parent)

    @Deprecated("deprecated in android plugins")
    public var targetSdk: Int by PropertyDelegate(parent)

    public var testInstrumentationRunner: String by PropertyDelegate(parent)

    public var testFunctionalTest: Boolean by PropertyDelegate(parent)

    public fun buildConfigField(type: String, name: String, value: String) {
        line("buildConfigField(${formatValue(type)}, ${formatValue(name)}, ${formatValue(value)})")
    }

    public fun resValue(type: String, name: String, value: String) {
        line("resValue(${formatValue(type)}, ${formatValue(name)}, ${formatValue(value)})")
    }

    public fun vectorDrawables(init: VectorDrawablesOptionsDsl.() -> Unit) {
        block("vectorDrawables") {
            VectorDrawablesOptionsDsl(this).apply(init)
        }
    }
}

public interface AndroidProductFlavor

public class AndroidLibraryProductFlavorsDsl(parent: GradleDsl) :
    AbstractNamedDomainObjectCollectionDsl<AndroidProductFlavor, AndroidLibraryProductFlavorDsl>(parent) {

    override fun createConfigurator(dsl: GradleDsl): AndroidLibraryProductFlavorDsl = AndroidLibraryProductFlavorDsl(dsl)

}

public class AndroidLibraryProductFlavorDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var dimension: String by PropertyDelegate(parent)

    public fun buildConfigField(type: String, name: String, value: String) {
        line("buildConfigField(${formatValue(type)}, ${formatValue(name)}, ${formatValue(value)})")
    }

    public fun resValue(type: String, name: String, value: String) {
        line("resValue(${formatValue(type)}, ${formatValue(name)}, ${formatValue(value)})")
    }
}

public class AndroidLibraryBuildTypesDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun release(init: AndroidLibraryBuildTypeDsl.() -> Unit) {
        block("release") {
            AndroidLibraryBuildTypeDsl(this).apply(init)
        }
    }

    public fun debug(init: AndroidLibraryBuildTypeDsl.() -> Unit) {
        block("debug") {
            AndroidLibraryBuildTypeDsl(this).apply(init)
        }
    }

    public fun create(name: String, init: AndroidLibraryBuildTypeDsl.() -> Unit) {
        block("create(${formatValue(name)})") {
            AndroidLibraryBuildTypeDsl(this).apply(init)
        }
    }
}

public class AndroidLibraryBuildTypeDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var isMinifyEnabled: Boolean by PropertyDelegate(parent)

    public var isDebuggable: Boolean by PropertyDelegate(parent)

    public var isJniDebuggable: Boolean by PropertyDelegate(parent)

    public var isShrinkResources: Boolean by PropertyDelegate(parent)

    public fun buildConfigField(type: String, name: String, value: String) {
        line("buildConfigField(${formatValue(type)}, ${formatValue(name)}, ${formatValue(value)})")
    }

    public fun resValue(type: String, name: String, value: String) {
        line("resValue(${formatValue(type)}, ${formatValue(name)}, ${formatValue(value)})")
    }

    public fun matchingFallbacks(vararg fallbacks: String) {
        val fallbacksStr = fallbacks.joinToString("\", \"", "\"", "\"")
        line("matchingFallbacks += listOf($fallbacksStr)")
    }
}