package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * DSL for Kotlin Multiplatform
 */
public class KotlinMultiplatformDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds JVM target
     */
    public fun jvm(init: (KotlinTargetDsl.() -> Unit)? = null) {
        if (init != null) {
            block("jvm()") {
                KotlinTargetDsl(this).apply(init)
            }
        } else {
            line("jvm()")
        }
    }

    /**
     * Adds Android target
     */
    public fun androidTarget(init: KotlinAndroidTargetDsl.() -> Unit) {
        block("androidTarget") {
            KotlinAndroidTargetDsl(this).apply(init)
        }
    }

    /**
     * Adds Linux x64 target
     */
    public fun linuxX64(init: (KotlinTargetDsl.() -> Unit)? = null) {
        if (init != null) {
            block("linuxX64()") {
                KotlinTargetDsl(this).apply(init)
            }
        } else {
            line("linuxX64()")
        }
    }

    /**
     * Adds iOS target
     */
    public fun ios(init: (KotlinTargetDsl.() -> Unit)? = null) {
        if (init != null) {
            block("ios()") {
                KotlinTargetDsl(this).apply(init)
            }
        } else {
            line("ios()")
        }
    }

    /**
     * Adds macOS x64 target
     */
    public fun macosX64(init: (KotlinTargetDsl.() -> Unit)? = null) {
        if (init != null) {
            block("macosX64()") {
                KotlinTargetDsl(this).apply(init)
            }
        } else {
            line("macosX64()")
        }
    }

    /**
     * Adds JavaScript target
     */
    public fun js(init: KotlinJsTargetDsl.() -> Unit) {
        block("js") {
            KotlinJsTargetDsl(this).apply(init)
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
}

/**
 * DSL for Kotlin Android targets
 */
public class KotlinAndroidTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {
    /**
     * Defines library variants to publish
     */
    public fun publishLibraryVariants(vararg variants: String) {
        val variantsStr = variants.joinToString("\", \"", "\"", "\"")
        line("publishLibraryVariants($variantsStr)")
    }
}

/**
 * DSL for Kotlin JS targets
 */
public class KotlinJsTargetDsl(parent: GradleDsl) : KotlinTargetDsl(parent) {
    /**
     * Configures browser target
     */
    public fun browser() {
        line("browser()")
    }

    /**
     * Configures Node.js target
     */
    public fun nodejs() {
        line("nodejs()")
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
        line("jvmTarget.set(JvmTarget.${target.value})")
    }

    /**
     * Enables experimental features
     */
    public fun enableExperimentalFeatures() {
        line("freeCompilerArgs.addAll(\"-Xopt-in=kotlin.ExperimentalUnsignedTypes\", \"-Xopt-in=kotlin.ExperimentalStdlibApi\")")
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
        block("commonMain") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures JVM source set
     */
    public fun jvmMain(init: KotlinSourceSetDsl.() -> Unit) {
        block("jvmMain") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures Android source set
     */
    public fun androidMain(init: KotlinSourceSetDsl.() -> Unit) {
        block("androidMain") {
            KotlinSourceSetDsl(this).apply(init)
        }
    }

    /**
     * Configures common test source set
     */
    public fun commonTest(init: KotlinSourceSetDsl.() -> Unit) {
        block("commonTest") {
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
     * Adds test implementation dependency
     */
    public fun testImplementation(dependency: String) {
        line("testImplementation(\"$dependency\")")
    }
}

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