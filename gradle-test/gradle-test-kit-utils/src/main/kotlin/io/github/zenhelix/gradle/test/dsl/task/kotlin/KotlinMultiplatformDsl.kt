package io.github.zenhelix.gradle.test.dsl.task.kotlin

import io.github.zenhelix.gradle.test.dsl.GradleDsl

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
     * Sets explicit API mode
     */
    public fun explicitApiWarning() {
        line("explicitApiWarning()")
    }

    /**
     * Sets explicit API mode with a specific mode
     */
    public fun explicitApi(mode: ExplicitApiMode) {
        line("explicitApi = org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode.${mode.value}")
    }

    /**
     * Adds JVM target
     */
    public fun jvm(name: String? = null, init: (KotlinJvmTargetDsl.() -> Unit)? = null) {
        if (name != null) {
            if (init != null) {
                block("jvm(\"$name\")") {
                    KotlinJvmTargetDsl(this).apply(init)
                }
            } else {
                line("jvm(\"$name\")")
            }
        } else {
            if (init != null) {
                block("jvm()") {
                    KotlinJvmTargetDsl(this).apply(init)
                }
            } else {
                line("jvm()")
            }
        }
    }

    /**
     * Adds Android target
     */
    public fun androidTarget(name: String? = null, init: KotlinAndroidTargetDsl.() -> Unit = {}) {
        if (name != null) {
            block("androidTarget(\"$name\")") {
                KotlinAndroidTargetDsl(this).apply(init)
            }
        } else {
            block("androidTarget()") {
                KotlinAndroidTargetDsl(this).apply(init)
            }
        }
    }

    /**
     * Adds Linux x64 target
     */
    public fun linuxX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("linuxX64", name, init)
    }

    /**
     * Adds Linux ARM 64 target
     */
    public fun linuxArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("linuxArm64", name, init)
    }

    /**
     * Adds iOS arm64 target
     */
    public fun iosArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("iosArm64", name, init)
    }

    /**
     * Adds iOS simulator arm64 target
     */
    public fun iosSimulatorArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("iosSimulatorArm64", name, init)
    }

    /**
     * Adds iOS x64 target
     */
    public fun iosX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("iosX64", name, init)
    }

    /**
     * Adds watchOS arm64 target
     */
    public fun watchosArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("watchosArm64", name, init)
    }

    /**
     * Adds watchOS x64 simulator target
     */
    public fun watchosX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("watchosX64", name, init)
    }

    /**
     * Adds watchOS simulator arm64 target
     */
    public fun watchosSimulatorArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("watchosSimulatorArm64", name, init)
    }

    /**
     * Adds tvOS arm64 target
     */
    public fun tvosArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("tvosArm64", name, init)
    }

    /**
     * Adds tvOS x64 simulator target
     */
    public fun tvosX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("tvosX64", name, init)
    }

    /**
     * Adds tvOS simulator arm64 target
     */
    public fun tvosSimulatorArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("tvosSimulatorArm64", name, init)
    }

    /**
     * Adds macOS x64 target
     */
    public fun macosX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("macosX64", name, init)
    }

    /**
     * Adds macOS arm64 target
     */
    public fun macosArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        nativeTarget("macosArm64", name, init)
    }

    /**
     * Adds JavaScript target
     */
    public fun js(name: String? = null, init: KotlinJsTargetDsl.() -> Unit = {}) {
        if (name != null) {
            block("js(\"$name\")") {
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
            block("wasmJs(\"$name\")") {
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
     * Adds a native target with optional configuration
     */
    private fun nativeTarget(targetName: String, name: String?, init: (KotlinNativeTargetDsl.() -> Unit)?) {
        if (name != null) {
            if (init != null) {
                block("$targetName(\"$name\")") {
                    KotlinNativeTargetDsl(this).apply(init)
                }
            } else {
                line("$targetName(\"$name\")")
            }
        } else {
            if (init != null) {
                block("$targetName()") {
                    KotlinNativeTargetDsl(this).apply(init)
                }
            } else {
                line("$targetName()")
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

}

/**
 * DSL for Kotlin JS common webpack configuration
 */
public class KotlinJsCommonWebpackDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the module kind
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
     * Enables CSS support
     */
    public fun enabled(value: Boolean) {
        line("enabled = $value")
    }
}

/**
 * DSL for Kotlin JS Webpack configuration
 */
public class KotlinJsWebpackDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the output filename
     */
    public fun mainOutputFileName(name: String) {
        line("mainOutputFileName = \"$name\"")
    }
}


/**
 * DSL for Kotlin JS distribution configuration
 */
public class KotlinJsDistributionDsl(private val parent: GradleDsl) : GradleDsl by parent {

}

/**
 * DSL for Kotlin JS executable configuration
 */
public class KotlinJsExecutableDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the module kind
     */
    public fun moduleKind(kind: KotlinJsModuleKind) {
        line("moduleKind = ModuleKind.${kind.name}")
    }
}

/**
 * DSL for Kotlin JS library configuration
 */
public class KotlinJsLibraryDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the module kind
     */
    public fun moduleKind(kind: KotlinJsModuleKind) {
        line("moduleKind = ModuleKind.${kind.name}")
    }
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
 * DSL for Kotlin Native executable configuration
 */
public open class KotlinNativeExecutableDsl(private val parent: GradleDsl) : GradleDsl by parent {

}

/**
 * Base class for Kotlin Native binary type configurations
 */
public open class KotlinNativeBaseConfigDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets compilation options
     */
    public fun compilation(target: String) {
        line("compilation = compilations.getByName(\"$target\")")
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
        line("entryPoint = \"$value\"")
    }
}

/**
 * DSL for Kotlin Native framework configuration
 */
public class KotlinNativeFrameworkDsl(private val parent: GradleDsl) : KotlinNativeExecutableDsl(parent)

/**
 * DSL for Kotlin Native shared library configuration
 */
public class KotlinNativeSharedLibDsl(private val parent: GradleDsl) : KotlinNativeExecutableDsl(parent)

/**
 * DSL for Kotlin Native static library configuration
 */
public class KotlinNativeStaticLibDsl(private val parent: GradleDsl) : KotlinNativeExecutableDsl(parent)

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
        block("getByName(\"$name\")") {
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
        line("defaultSourceSet = sourceSets.getByName(\"$sourceSets\")")
    }

    /**
     * Adds a source set to the compilation
     */
    public fun associateWith(sourceSet: String) {
        line("associateWith(sourceSets.getByName(\"$sourceSet\"))")
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
        line("attribute(Attribute.of(\"$key\", String::class.java), \"$value\")")
    }
}

/**
 * DSL for Kotlin compiler options
 */
public class KotlinCompilerOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets JVM target version
     * @param target the JVM target version from the JvmTarget enum
     */
    public fun jvmTarget(target: KotlinJvmTarget) {
        line("jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.${target.value}")
    }

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
     * Sets language version
     */
    public fun languageVersion(version: String) {
        line("languageVersion = \"$version\"")
    }

    /**
     * Sets API version
     */
    public fun apiVersion(version: String) {
        line("apiVersion = \"$version\"")
    }

    /**
     * Enables or disables allWarningsAsErrors
     */
    public fun allWarningsAsErrors(value: Boolean) {
        line("allWarningsAsErrors = $value")
    }

    /**
     * Enables or disables progressive mode
     */
    public fun progressiveMode(value: Boolean) {
        line("progressiveMode = $value")
    }

    /**
     * Sets useK2 compiler flag
     */
    public fun useK2(value: Boolean) {
        line("useK2 = $value")
    }
}

/**
 * DSL for Kotlin source sets
 */
public class KotlinSourceSetsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures common source set
     */
    public fun commonMain(init: KotlinSourceSetDsl.() -> Unit) {
        block("val commonMain by getting") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures JVM source set
     */
    public fun jvmMain(init: KotlinSourceSetDsl.() -> Unit) {
        block("getByName(\"jvmMain\")") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures Android source set
     */
    public fun androidMain(init: KotlinSourceSetDsl.() -> Unit) {
        block("getByName(\"androidMain\")") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures common test source set
     */
    public fun commonTest(init: KotlinSourceSetDsl.() -> Unit) {
        block("getByName(\"commonTest\")") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures JVM test source set
     */
    public fun jvmTest(init: KotlinSourceSetDsl.() -> Unit) {
        block("getByName(\"jvmTest\")") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures Android test source set
     */
    public fun androidTest(init: KotlinSourceSetDsl.() -> Unit) {
        block("getByName(\"androidTest\")") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures a named source set
     */
    public fun getByName(name: String, init: KotlinSourceSetDsl.() -> Unit) {
        block("getByName(\"$name\")") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Creates a source set
     */
    public fun create(name: String, init: KotlinSourceSetDsl.() -> Unit) {
        block("create(\"$name\")") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Kotlin source set
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
        line("kotlin.srcDir(\"$srcDir\")")
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
        line("resources.srcDir(\"$srcDir\")")
    }

    /**
     * Sets resource directories
     */
    public fun resources(vararg srcDirs: String) {
        val dirsStr = srcDirs.joinToString("\", \"", "\"", "\"")
        line("resources.setSrcDirs(listOf($dirsStr))")
    }

    /**
     * Defines source set dependencies
     */
    public fun dependsOn(sourceSet: String) {
        line("dependsOn(sourceSets.getByName(\"$sourceSet\"))")
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
        line("implementation(\"$dependency\")")
    }

    /**
     * Adds API dependency
     */
    public fun api(dependency: String) {
        line("api(\"$dependency\")")
    }

    /**
     * Adds compileOnly dependency
     */
    public fun compileOnly(dependency: String) {
        line("compileOnly(\"$dependency\")")
    }

    /**
     * Adds runtimeOnly dependency
     */
    public fun runtimeOnly(dependency: String) {
        line("runtimeOnly(\"$dependency\")")
    }

    /**
     * Adds test implementation dependency
     */
    public fun testImplementation(dependency: String) {
        line("testImplementation(\"$dependency\")")
    }

    /**
     * Adds a project dependency
     */
    public fun implementation(project: ProjectDependency) {
        line("implementation(project(\"${project.path}\"))")
    }

    /**
     * Adds a project API dependency
     */
    public fun api(project: ProjectDependency) {
        line("api(project(\"${project.path}\"))")
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