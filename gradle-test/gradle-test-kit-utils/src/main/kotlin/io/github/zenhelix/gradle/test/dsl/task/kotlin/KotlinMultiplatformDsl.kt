package io.github.zenhelix.gradle.test.dsl.task.kotlin

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractNamedDomainObjectCollectionDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate

/**
 * Enum defining Kotlin Native platforms
 */
public enum class KotlinNativePlatform(public val id: String) {
    LINUX_X64("linuxX64"),
    LINUX_ARM64("linuxArm64"),
    IOS_ARM64("iosArm64"),
    IOS_X64("iosX64"),
    IOS_SIMULATOR_ARM64("iosSimulatorArm64"),
    WATCHOS_ARM64("watchosArm64"),
    WATCHOS_X64("watchosX64"),
    WATCHOS_SIMULATOR_ARM64("watchosSimulatorArm64"),
    TVOS_ARM64("tvosArm64"),
    TVOS_X64("tvosX64"),
    TVOS_SIMULATOR_ARM64("tvosSimulatorArm64"),
    MACOS_X64("macosX64"),
    MACOS_ARM64("macosArm64");
}

/**
 * DSL for Kotlin Multiplatform
 */
public class KotlinMultiplatformDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets explicit API mode
     */
    public fun explicitApi() {
        line("explicitApi()")
    }

    /**
     * Sets explicit API mode with warning
     */
    public fun explicitApiWarning() {
        line("explicitApiWarning()")
    }

    /**
     * Sets explicit API mode with a specific mode
     */
    public var explicitApi: ExplicitApiMode by PropertyDelegate(parent) { "org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode.${it.value}" }

    /**
     * Adds JVM target
     */
    public fun jvm(name: String? = null, init: KotlinJvmTargetDsl.() -> Unit = {}) {
        if (name != null) {
            block("jvm(${formatValue(name)})") {
                KotlinJvmTargetDsl(this).apply(init)
            }
        } else {
            block("jvm()") {
                KotlinJvmTargetDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds Android target
     */
    public fun androidTarget(name: String? = null, init: KotlinAndroidTargetDsl.() -> Unit = {}) {
        if (name != null) {
            block("androidTarget(${formatValue(name)})") {
                KotlinAndroidTargetDsl(this).apply(init)
            }
        } else {
            block("androidTarget()") {
                KotlinAndroidTargetDsl(this).apply(init)
            }
        }
    }


    /**
     * Adds Linux x64 target (convenience method)
     */
    public fun linuxX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.LINUX_X64, name, init)
    }

    /**
     * Adds Linux ARM 64 target (convenience method)
     */
    public fun linuxArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.LINUX_ARM64, name, init)
    }

    /**
     * Adds iOS arm64 target (convenience method)
     */
    public fun iosArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.IOS_ARM64, name, init)
    }

    /**
     * Adds iOS simulator arm64 target (convenience method)
     */
    public fun iosSimulatorArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.IOS_SIMULATOR_ARM64, name, init)
    }

    /**
     * Adds iOS x64 target (convenience method)
     */
    public fun iosX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.IOS_X64, name, init)
    }

    /**
     * Adds JavaScript target
     */
    public fun js(name: String? = null, init: KotlinJsTargetDsl.() -> Unit = {}) {
        if (name != null) {
            block("js(${formatValue(name)})") {
                KotlinJsTargetDsl(this).apply(init)
            }
        } else {
            block("js()") {
                KotlinJsTargetDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds WebAssembly target
     */
    public fun wasmJs(name: String? = null, init: KotlinWasmJsTargetDsl.() -> Unit = {}) {
        if (name != null) {
            block("wasmJs(${formatValue(name)})") {
                KotlinWasmJsTargetDsl(this).apply(init)
            }
        } else {
            block("wasmJs()") {
                KotlinWasmJsTargetDsl(this).apply(init)
            }
        }
    }

    /**
     * Configures source sets
     */
    public fun sourceSets(init: KotlinSourceSetsDsl.() -> Unit) {
        block("sourceSets") {
            KotlinSourceSetsDsl(this).apply(init)
        }
    }


    /**
     * Adds a native target with the specified platform
     */
    public fun native(platform: KotlinNativePlatform, name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        if (name != null) {
            if (init == null) {
                line("${platform.id}(${formatValue(name)})")
            } else {
                block("${platform.id}(${formatValue(name)})") {
                    KotlinNativeTargetDsl(this).apply(init)
                }
            }
        } else {
            if (init == null) {
                line("${platform.id}()")
            } else {
                block("${platform.id}()") {
                    KotlinNativeTargetDsl(this).apply(init)
                }
            }
        }
    }

}

/**
 * Enumeration of explicit API modes
 */
public enum class ExplicitApiMode(internal val value: String) {
    STRICT("Strict"),
    WARNING("Warning"),
    DISABLED("Disabled");
}

/**
 * DSL for Kotlin targets
 */
public open class KotlinTargetDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures compiler options
     */
    public fun compilerOptions(init: KotlinCompilerOptionsDsl.() -> Unit) {
        block("compilerOptions") {
            KotlinCompilerOptionsDsl(this).apply(init)
        }
    }

    /**
     * Configures compilations
     */
    public fun compilations(init: KotlinCompilationsDsl.() -> Unit) {
        block("compilations") {
            KotlinCompilationsDsl(this).apply(init)
        }
    }

    /**
     * Sets target attributes
     */
    public fun attributes(init: KotlinAttributesDsl.() -> Unit) {
        block("attributes") {
            KotlinAttributesDsl(this).apply(init)
        }
    }

    /**
     * Determines if the target should be published separately
     */
    public fun withJavaEnabled() {
        line("withJava()")
    }
}

/**
 * DSL for Kotlin JVM targets
 */
public class KotlinJvmTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {
    // JVM-specific configurations go here
}

/**
 * DSL for Kotlin Android targets
 */
public class KotlinAndroidTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {
    /**
     * Defines library variants to publish
     */
    public fun publishLibraryVariants(vararg variants: String) {
        if (variants.isEmpty()) {
            line("publishLibraryVariants()")
        } else {
            val variantsStr = variants.joinToString("\", \"", "\"", "\"")
            line("publishLibraryVariants($variantsStr)")
        }
    }

    /**
     * Publishes all library variants with sources
     */
    public fun publishAllLibraryVariants() {
        line("publishAllLibraryVariants()")
    }
}

public class KotlinWasmJsTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {
    /**
     * Configures browser target
     */
    public fun browser(init: KotlinJsBrowserDsl.() -> Unit = {}) {
        block("browser") {
            KotlinJsBrowserDsl(this).apply(init)
        }
    }

    /**
     * Configures Node.js target
     */
    public fun nodejs(init: KotlinJsNodeDsl.() -> Unit = {}) {
        block("nodejs") {
            KotlinJsNodeDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Kotlin JS targets
 */
public class KotlinJsTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {
    /**
     * Configures browser target
     */
    public fun browser(init: KotlinJsBrowserDsl.() -> Unit = {}) {
        block("browser") {
            KotlinJsBrowserDsl(this).apply(init)
        }
    }

    /**
     * Configures Node.js target
     */
    public fun nodejs(init: KotlinJsNodeDsl.() -> Unit = {}) {
        block("nodejs") {
            KotlinJsNodeDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Kotlin JS Browser configuration
 */
public class KotlinJsBrowserDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures webpack
     */
    public fun webpackTask(init: KotlinJsWebpackDsl.() -> Unit) {
        block("webpackTask") {
            KotlinJsWebpackDsl(this).apply(init)
        }
    }

    /**
     * Configures distribution settings
     */
    public fun distribution(init: KotlinJsDistributionDsl.() -> Unit) {
        block("distribution") {
            KotlinJsDistributionDsl(this).apply(init)
        }
    }

    /**
     * Enables CommonJS module kind
     */
    public fun commonWebpackConfig(init: KotlinJsCommonWebpackDsl.() -> Unit) {
        block("commonWebpackConfig") {
            KotlinJsCommonWebpackDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Kotlin JS Node configuration
 */
public class KotlinJsNodeDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Enables the test task
     */
    public fun testTask(init: KotlinJsNodeTestTaskDsl.() -> Unit) {
        block("testTask") {
            KotlinJsNodeTestTaskDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Kotlin JS Node test task configuration
 */
public class KotlinJsNodeTestTaskDsl(private val parent: GradleDsl) : GradleDsl by parent {
    // Node test task configuration
}

/**
 * DSL for Kotlin JS common webpack configuration
 */
public class KotlinJsCommonWebpackDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures CSS support
     */
    public fun cssSupport(init: KotlinJsCssSupportDsl.() -> Unit) {
        block("cssSupport") {
            KotlinJsCssSupportDsl(this).apply(init)
        }
    }

    /**
     * Adds dynamic imports
     */
    public fun enableDynamicImport() {
        line("enableDynamicImport()")
    }
}

/**
 * DSL for Kotlin JS CSS Support
 */
public class KotlinJsCssSupportDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * CSS support enabled property
     */
    public var enabled: Boolean by PropertyDelegate(parent)
}

/**
 * DSL for Kotlin JS Webpack configuration
 */
public class KotlinJsWebpackDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Main output filename property
     */
    public var mainOutputFileName: String by PropertyDelegate(parent)
}

/**
 * DSL for Kotlin JS distribution configuration
 */
public class KotlinJsDistributionDsl(private val parent: GradleDsl) : GradleDsl by parent {
    // Distribution-specific configurations
}

/**
 * DSL for Kotlin JS executable configuration
 */
public class KotlinJsExecutableDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Module kind property
     */
    public var moduleKind: KotlinJsModuleKind by PropertyDelegate(parent) { "ModuleKind.${it.name}" }
}

/**
 * DSL for Kotlin JS library configuration
 */
public class KotlinJsLibraryDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Module kind property
     */
    public var moduleKind: KotlinJsModuleKind by PropertyDelegate(parent) { "ModuleKind.${it.name}" }
}

/**
 * Enumeration of JavaScript module kinds
 */
public enum class KotlinJsModuleKind {
    PLAIN,
    AMD,
    COMMON_JS,
    UMD,
    ES
}

/**
 * DSL for Kotlin Native targets
 */
public class KotlinNativeTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {
    /**
     * Configures binaries
     */
    public fun binaries(init: KotlinNativeBinariesDsl.() -> Unit) {
        block("binaries") {
            KotlinNativeBinariesDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Kotlin Native binaries
 */
public class KotlinNativeBinariesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures executable binaries
     */
    public fun executable(init: KotlinNativeExecutableDsl.() -> Unit = {}) {
        block("executable") {
            KotlinNativeExecutableDsl(this).apply(init)
        }
    }

    /**
     * Configures framework binaries
     */
    public fun framework(init: KotlinNativeFrameworkDsl.() -> Unit = {}) {
        block("framework") {
            KotlinNativeFrameworkDsl(this).apply(init)
        }
    }

    /**
     * Configures shared library binaries
     */
    public fun sharedLib(init: KotlinNativeSharedLibDsl.() -> Unit = {}) {
        block("sharedLib") {
            KotlinNativeSharedLibDsl(this).apply(init)
        }
    }

    /**
     * Configures static library binaries
     */
    public fun staticLib(init: KotlinNativeStaticLibDsl.() -> Unit = {}) {
        block("staticLib") {
            KotlinNativeStaticLibDsl(this).apply(init)
        }
    }
}

/**
 * Base class for native binary configurations
 */
public open class KotlinNativeBinaryDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets compilation options
     */
    public fun compilation(target: String) {
        line("compilation = compilations.getByName(${formatValue(target)})")
    }

    /**
     * Sets linked libraries
     */
    public fun linkerOpts(vararg options: String) {
        val optsStr = options.joinToString("\", \"", "\"", "\"")
        line("linkerOpts(listOf($optsStr))")
    }

    /**
     * Sets entryPoint
     */
    public fun entryPoint(value: String) {
        line("entryPoint = ${formatValue(value)}")
    }
}

/**
 * DSL for Kotlin Native executable configuration
 */
public class KotlinNativeExecutableDsl(parent: GradleDsl) : KotlinNativeBinaryDsl(parent)

/**
 * DSL for Kotlin Native framework configuration
 */
public class KotlinNativeFrameworkDsl(parent: GradleDsl) : KotlinNativeBinaryDsl(parent)

/**
 * DSL for Kotlin Native shared library configuration
 */
public class KotlinNativeSharedLibDsl(parent: GradleDsl) : KotlinNativeBinaryDsl(parent)

/**
 * DSL for Kotlin Native static library configuration
 */
public class KotlinNativeStaticLibDsl(parent: GradleDsl) : KotlinNativeBinaryDsl(parent)

/**
 * DSL for Kotlin compilations
 */
public class KotlinCompilationsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures the main compilation
     */
    public fun main(init: KotlinCompilationDsl.() -> Unit) {
        block("getByName(\"main\")") {
            KotlinCompilationDsl(this).apply(init)
        }
    }

    /**
     * Configures the test compilation
     */
    public fun test(init: KotlinCompilationDsl.() -> Unit) {
        block("getByName(\"test\")") {
            KotlinCompilationDsl(this).apply(init)
        }
    }

    /**
     * Configures all compilations
     */
    public fun all(init: KotlinCompilationDsl.() -> Unit) {
        block("all") {
            KotlinCompilationDsl(this).apply(init)
        }
    }

    /**
     * Configures a named compilation
     */
    public fun getByName(name: String, init: KotlinCompilationDsl.() -> Unit) {
        block("getByName(${formatValue(name)})") {
            KotlinCompilationDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Kotlin compilation
 */
public class KotlinCompilationDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the compilation default source set
     */
    public fun defaultSourceSet(sourceSets: String) {
        line("defaultSourceSet = sourceSets.getByName(${formatValue(sourceSets)})")
    }

    /**
     * Adds a source set to the compilation
     */
    public fun associateWith(sourceSet: String) {
        line("associateWith(sourceSets.getByName(${formatValue(sourceSet)}))")
    }

    /**
     * Configures compiler options
     */
    public fun compilerOptions(init: KotlinCompilerOptionsDsl.() -> Unit) {
        block("compilerOptions") {
            KotlinCompilerOptionsDsl(this).apply(init)
        }
    }

    /**
     * Sets compiler dependencies
     */
    public fun kotlinOptions(init: KotlinCompilerOptionsDsl.() -> Unit) {
        block("kotlinOptions") {
            KotlinCompilerOptionsDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Kotlin attributes
 */
public class KotlinAttributesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds an attribute
     */
    public fun attribute(key: String, value: String) {
        line("attribute(Attribute.of(${formatValue(key)}, String::class.java), ${formatValue(value)})")
    }
}

/**
 * DSL for Kotlin compiler options
 */
public class KotlinCompilerOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * JVM target property
     */
    public var jvmTarget: KotlinJvmTarget by PropertyDelegate(parent) { "org.jetbrains.kotlin.gradle.dsl.JvmTarget.${it.value}" }

    /**
     * Enables experimental features
     */
    public fun enableExperimentalFeatures() {
        line("freeCompilerArgs.addAll(\"-Xopt-in=kotlin.ExperimentalUnsignedTypes\", \"-Xopt-in=kotlin.ExperimentalStdlibApi\")")
    }

    /**
     * Adds compiler arguments
     */
    public fun freeCompilerArgs(vararg args: String) {
        val argsStr = args.joinToString("\", \"", "\"", "\"")
        line("freeCompilerArgs.addAll($argsStr)")
    }

    /**
     * Language version property
     */
    public var languageVersion: String by PropertyDelegate(parent)

    /**
     * API version property
     */
    public var apiVersion: String by PropertyDelegate(parent)

    /**
     * Warnings as errors property
     */
    public var allWarningsAsErrors: Boolean by PropertyDelegate(parent)

    /**
     * Progressive mode property
     */
    public var progressiveMode: Boolean by PropertyDelegate(parent)

    /**
     * K2 compiler property
     */
    public var useK2: Boolean by PropertyDelegate(parent)
}

/**
 * DSL for Kotlin source sets
 */
public class KotlinSourceSetsDsl(parent: GradleDsl) :
    AbstractNamedDomainObjectCollectionDsl<KotlinSourceSet, KotlinSourceSetDsl>(parent, "sourceSets") {

    /**
     * Creates the configurator for a source set
     */
    override fun createConfigurator(dsl: GradleDsl): KotlinSourceSetDsl = KotlinSourceSetDsl(dsl)

    /**
     * Configures common main source set using the 'getting' pattern
     */
    public fun commonMain(init: KotlinSourceSetDsl.() -> Unit) {
        getting("commonMain", init)
    }

    /**
     * Configures JVM main source set
     */
    public fun jvmMain(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("jvmMain", init)
    }

    /**
     * Configures Android main source set
     */
    public fun androidMain(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("androidMain", init)
    }

    /**
     * Configures common test source set
     */
    public fun commonTest(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("commonTest", init)
    }

    /**
     * Configures JVM test source set
     */
    public fun jvmTest(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("jvmTest", init)
    }

    /**
     * Configures Android test source set
     */
    public fun androidTest(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("androidTest", init)
    }
}

/**
 * Marker interface for Kotlin source set type
 */
public interface KotlinSourceSet

/**
 * DSL for Kotlin source set - remains unchanged
 */
public class KotlinSourceSetDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures dependencies
     */
    public fun dependencies(init: KotlinDependenciesDsl.() -> Unit) {
        block("dependencies") {
            KotlinDependenciesDsl(this).apply(init)
        }
    }

    /**
     * Configures source directories
     */
    public fun kotlin(srcDir: String) {
        line("kotlin.srcDir(${formatValue(srcDir)})")
    }

    /**
     * Sets source directories
     */
    public fun kotlin(vararg srcDirs: String) {
        val dirsStr = srcDirs.joinToString("\", \"", "\"", "\"")
        line("kotlin.setSrcDirs(listOf($dirsStr))")
    }

    /**
     * Configures resource directories
     */
    public fun resources(srcDir: String) {
        line("resources.srcDir(${formatValue(srcDir)})")
    }

    /**
     * Sets resource directories
     */
    public fun resources(vararg srcDirs: String) {
        val dirsStr = srcDirs.joinToString("\", \"", "\"", "\"")
        line("resources.setSrcDirs(listOf($dirsStr))")
    }

}

/**
 * DSL for Kotlin dependencies
 */
public class KotlinDependenciesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds implementation dependency
     */
    public fun implementation(dependency: String) {
        line("implementation(${formatValue(dependency)})")
    }

    /**
     * Adds API dependency
     */
    public fun api(dependency: String) {
        line("api(${formatValue(dependency)})")
    }

    /**
     * Adds compileOnly dependency
     */
    public fun compileOnly(dependency: String) {
        line("compileOnly(${formatValue(dependency)})")
    }

    /**
     * Adds runtimeOnly dependency
     */
    public fun runtimeOnly(dependency: String) {
        line("runtimeOnly(${formatValue(dependency)})")
    }

    /**
     * Adds test implementation dependency
     */
    public fun testImplementation(dependency: String) {
        line("testImplementation(${formatValue(dependency)})")
    }

    /**
     * Adds a project dependency
     */
    public fun implementation(project: ProjectDependency) {
        line("implementation(project(${formatValue(project.path)}))")
    }

    /**
     * Adds a project API dependency
     */
    public fun api(project: ProjectDependency) {
        line("api(project(${formatValue(project.path)}))")
    }
}

/**
 * Class representing a project dependency
 */
public class ProjectDependency(public val path: String)

public enum class KotlinJvmTarget(internal val value: String) {
    JVM_1_8("JVM_1_8"),
    JVM_9("JVM_9"),
    JVM_10("JVM_10"),
    JVM_11("JVM_11"),
    JVM_12("JVM_12"),
    JVM_13("JVM_13"),
    JVM_14("JVM_14"),
    JVM_15("JVM_15"),
    JVM_16("JVM_16"),
    JVM_17("JVM_17"),
    JVM_18("JVM_18"),
    JVM_19("JVM_19"),
    JVM_20("JVM_20"),
    JVM_21("JVM_21");
}