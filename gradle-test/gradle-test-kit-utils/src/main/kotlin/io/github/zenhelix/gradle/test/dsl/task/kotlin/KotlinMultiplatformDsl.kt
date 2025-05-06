package io.github.zenhelix.gradle.test.dsl.task.kotlin

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractNamedDomainObjectCollectionDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate

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

public class KotlinMultiplatformDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun explicitApi() {
        line("explicitApi()")
    }

    public fun explicitApiWarning() {
        line("explicitApiWarning()")
    }

    public var explicitApi: ExplicitApiMode by PropertyDelegate(parent) { "org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode.${it.value}" }

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

    public fun linuxX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.LINUX_X64, name, init)
    }

    public fun linuxArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.LINUX_ARM64, name, init)
    }

    public fun iosArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.IOS_ARM64, name, init)
    }

    public fun iosSimulatorArm64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.IOS_SIMULATOR_ARM64, name, init)
    }

    public fun iosX64(name: String? = null, init: (KotlinNativeTargetDsl.() -> Unit)? = null) {
        native(KotlinNativePlatform.IOS_X64, name, init)
    }

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

    public fun sourceSets(init: KotlinSourceSetsDsl.() -> Unit) {
        block("sourceSets") {
            KotlinSourceSetsDsl(this).apply(init)
        }
    }

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

public enum class ExplicitApiMode(internal val value: String) {
    STRICT("Strict"),
    WARNING("Warning"),
    DISABLED("Disabled");
}

public open class KotlinTargetDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun compilerOptions(init: KotlinCompilerOptionsDsl.() -> Unit) {
        block("compilerOptions") {
            KotlinCompilerOptionsDsl(this).apply(init)
        }
    }

    public fun compilations(init: KotlinCompilationsDsl.() -> Unit) {
        block("compilations") {
            KotlinCompilationsDsl(this).apply(init)
        }
    }

    public fun attributes(init: KotlinAttributesDsl.() -> Unit) {
        block("attributes") {
            KotlinAttributesDsl(this).apply(init)
        }
    }

    public fun withJavaEnabled() {
        line("withJava()")
    }
}

public class KotlinJvmTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {
    // JVM-specific configurations go here
}

public class KotlinAndroidTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {

    public fun publishLibraryVariants(vararg variants: String) {
        if (variants.isEmpty()) {
            line("publishLibraryVariants()")
        } else {
            val variantsStr = variants.joinToString("\", \"", "\"", "\"")
            line("publishLibraryVariants($variantsStr)")
        }
    }

    public fun publishAllLibraryVariants() {
        line("publishAllLibraryVariants()")
    }
}

public class KotlinWasmJsTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {

    public fun browser(init: KotlinJsBrowserDsl.() -> Unit = {}) {
        block("browser") {
            KotlinJsBrowserDsl(this).apply(init)
        }
    }

    public fun nodejs(init: KotlinJsNodeDsl.() -> Unit = {}) {
        block("nodejs") {
            KotlinJsNodeDsl(this).apply(init)
        }
    }
}

public class KotlinJsTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {

    public fun browser(init: KotlinJsBrowserDsl.() -> Unit = {}) {
        block("browser") {
            KotlinJsBrowserDsl(this).apply(init)
        }
    }

    public fun nodejs(init: KotlinJsNodeDsl.() -> Unit = {}) {
        block("nodejs") {
            KotlinJsNodeDsl(this).apply(init)
        }
    }
}

public class KotlinJsBrowserDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun webpackTask(init: KotlinJsWebpackDsl.() -> Unit) {
        block("webpackTask") {
            KotlinJsWebpackDsl(this).apply(init)
        }
    }

    public fun distribution(init: KotlinJsDistributionDsl.() -> Unit) {
        block("distribution") {
            KotlinJsDistributionDsl(this).apply(init)
        }
    }

    public fun commonWebpackConfig(init: KotlinJsCommonWebpackDsl.() -> Unit) {
        block("commonWebpackConfig") {
            KotlinJsCommonWebpackDsl(this).apply(init)
        }
    }
}

public class KotlinJsNodeDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun testTask(init: KotlinJsNodeTestTaskDsl.() -> Unit) {
        block("testTask") {
            KotlinJsNodeTestTaskDsl(this).apply(init)
        }
    }
}

public class KotlinJsNodeTestTaskDsl(private val parent: GradleDsl) : GradleDsl by parent {
    // Node test task configuration
}

public class KotlinJsCommonWebpackDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun cssSupport(init: KotlinJsCssSupportDsl.() -> Unit) {
        block("cssSupport") {
            KotlinJsCssSupportDsl(this).apply(init)
        }
    }

    public fun enableDynamicImport() {
        line("enableDynamicImport()")
    }
}

public class KotlinJsCssSupportDsl(private val parent: GradleDsl) : GradleDsl by parent {
    public var enabled: Boolean by PropertyDelegate(parent)
}

public class KotlinJsWebpackDsl(private val parent: GradleDsl) : GradleDsl by parent {
    public var mainOutputFileName: String by PropertyDelegate(parent)
}

public class KotlinJsDistributionDsl(private val parent: GradleDsl) : GradleDsl by parent {
    // Distribution-specific configurations
}

public class KotlinJsExecutableDsl(private val parent: GradleDsl) : GradleDsl by parent {
    public var moduleKind: KotlinJsModuleKind by PropertyDelegate(parent) { "ModuleKind.${it.name}" }
}

public class KotlinJsLibraryDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var moduleKind: KotlinJsModuleKind by PropertyDelegate(parent) { "ModuleKind.${it.name}" }
}

public enum class KotlinJsModuleKind {
    PLAIN,
    AMD,
    COMMON_JS,
    UMD,
    ES
}

public class KotlinNativeTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {

    public fun binaries(init: KotlinNativeBinariesDsl.() -> Unit) {
        block("binaries") {
            KotlinNativeBinariesDsl(this).apply(init)
        }
    }
}

public class KotlinNativeBinariesDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun executable(init: KotlinNativeExecutableDsl.() -> Unit = {}) {
        block("executable") {
            KotlinNativeExecutableDsl(this).apply(init)
        }
    }

    public fun framework(init: KotlinNativeFrameworkDsl.() -> Unit = {}) {
        block("framework") {
            KotlinNativeFrameworkDsl(this).apply(init)
        }
    }

    public fun sharedLib(init: KotlinNativeSharedLibDsl.() -> Unit = {}) {
        block("sharedLib") {
            KotlinNativeSharedLibDsl(this).apply(init)
        }
    }

    public fun staticLib(init: KotlinNativeStaticLibDsl.() -> Unit = {}) {
        block("staticLib") {
            KotlinNativeStaticLibDsl(this).apply(init)
        }
    }
}

public open class KotlinNativeBinaryDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun compilation(target: String) {
        line("compilation = compilations.getByName(${formatValue(target)})")
    }

    public fun linkerOpts(vararg options: String) {
        val optsStr = options.joinToString("\", \"", "\"", "\"")
        line("linkerOpts(listOf($optsStr))")
    }

    public fun entryPoint(value: String) {
        line("entryPoint = ${formatValue(value)}")
    }
}

public class KotlinNativeExecutableDsl(parent: GradleDsl) : KotlinNativeBinaryDsl(parent)

public class KotlinNativeFrameworkDsl(parent: GradleDsl) : KotlinNativeBinaryDsl(parent)

public class KotlinNativeSharedLibDsl(parent: GradleDsl) : KotlinNativeBinaryDsl(parent)

public class KotlinNativeStaticLibDsl(parent: GradleDsl) : KotlinNativeBinaryDsl(parent)

public class KotlinCompilationsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun main(init: KotlinCompilationDsl.() -> Unit) {
        block("getByName(\"main\")") {
            KotlinCompilationDsl(this).apply(init)
        }
    }

    public fun test(init: KotlinCompilationDsl.() -> Unit) {
        block("getByName(\"test\")") {
            KotlinCompilationDsl(this).apply(init)
        }
    }

    public fun all(init: KotlinCompilationDsl.() -> Unit) {
        block("all") {
            KotlinCompilationDsl(this).apply(init)
        }
    }

    public fun getByName(name: String, init: KotlinCompilationDsl.() -> Unit) {
        block("getByName(${formatValue(name)})") {
            KotlinCompilationDsl(this).apply(init)
        }
    }
}

public class KotlinCompilationDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun defaultSourceSet(sourceSets: String) {
        line("defaultSourceSet = sourceSets.getByName(${formatValue(sourceSets)})")
    }

    public fun associateWith(sourceSet: String) {
        line("associateWith(sourceSets.getByName(${formatValue(sourceSet)}))")
    }

    public fun compilerOptions(init: KotlinCompilerOptionsDsl.() -> Unit) {
        block("compilerOptions") {
            KotlinCompilerOptionsDsl(this).apply(init)
        }
    }

    public fun kotlinOptions(init: KotlinCompilerOptionsDsl.() -> Unit) {
        block("kotlinOptions") {
            KotlinCompilerOptionsDsl(this).apply(init)
        }
    }
}

public class KotlinAttributesDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun attribute(key: String, value: String) {
        line("attribute(Attribute.of(${formatValue(key)}, String::class.java), ${formatValue(value)})")
    }
}

public class KotlinCompilerOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var jvmTarget: KotlinJvmTarget by PropertyDelegate(parent) { "org.jetbrains.kotlin.gradle.dsl.JvmTarget.${it.value}" }

    public fun enableExperimentalFeatures() {
        line("freeCompilerArgs.addAll(\"-Xopt-in=kotlin.ExperimentalUnsignedTypes\", \"-Xopt-in=kotlin.ExperimentalStdlibApi\")")
    }

    public fun freeCompilerArgs(vararg args: String) {
        val argsStr = args.joinToString("\", \"", "\"", "\"")
        line("freeCompilerArgs.addAll($argsStr)")
    }

    public var languageVersion: String by PropertyDelegate(parent)

    public var apiVersion: String by PropertyDelegate(parent)

    public var allWarningsAsErrors: Boolean by PropertyDelegate(parent)

    public var progressiveMode: Boolean by PropertyDelegate(parent)

    public var useK2: Boolean by PropertyDelegate(parent)
}

public class KotlinSourceSetsDsl(parent: GradleDsl) :
    AbstractNamedDomainObjectCollectionDsl<KotlinSourceSet, KotlinSourceSetDsl>(parent, "sourceSets") {

    override fun createConfigurator(dsl: GradleDsl): KotlinSourceSetDsl = KotlinSourceSetDsl(dsl)

    public fun commonMain(init: KotlinSourceSetDsl.() -> Unit) {
        getting("commonMain", init)
    }

    public fun jvmMain(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("jvmMain", init)
    }

    public fun androidMain(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("androidMain", init)
    }

    public fun commonTest(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("commonTest", init)
    }

    public fun jvmTest(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("jvmTest", init)
    }

    public fun androidTest(init: KotlinSourceSetDsl.() -> Unit) {
        getByName("androidTest", init)
    }
}

public interface KotlinSourceSet

public class KotlinSourceSetDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun dependencies(init: KotlinDependenciesDsl.() -> Unit) {
        block("dependencies") {
            KotlinDependenciesDsl(this).apply(init)
        }
    }

    public fun kotlin(srcDir: String) {
        line("kotlin.srcDir(${formatValue(srcDir)})")
    }

    public fun kotlin(vararg srcDirs: String) {
        val dirsStr = srcDirs.joinToString("\", \"", "\"", "\"")
        line("kotlin.setSrcDirs(listOf($dirsStr))")
    }

    public fun resources(srcDir: String) {
        line("resources.srcDir(${formatValue(srcDir)})")
    }

    public fun resources(vararg srcDirs: String) {
        val dirsStr = srcDirs.joinToString("\", \"", "\"", "\"")
        line("resources.setSrcDirs(listOf($dirsStr))")
    }

}

public class KotlinDependenciesDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun implementation(dependency: String) {
        line("implementation(${formatValue(dependency)})")
    }

    public fun api(dependency: String) {
        line("api(${formatValue(dependency)})")
    }

    public fun compileOnly(dependency: String) {
        line("compileOnly(${formatValue(dependency)})")
    }

    public fun runtimeOnly(dependency: String) {
        line("runtimeOnly(${formatValue(dependency)})")
    }

    public fun testImplementation(dependency: String) {
        line("testImplementation(${formatValue(dependency)})")
    }

    public fun implementation(project: ProjectDependency) {
        line("implementation(project(${formatValue(project.path)}))")
    }

    public fun api(project: ProjectDependency) {
        line("api(project(${formatValue(project.path)}))")
    }
}

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