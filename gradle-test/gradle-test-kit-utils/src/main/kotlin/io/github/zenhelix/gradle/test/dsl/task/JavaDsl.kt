package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import org.gradle.api.JavaVersion

/**
 * DSL for Java
 */
public class JavaDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets source compatibility
     */
    public fun sourceCompatibility(version: JavaVersion) {
        line("sourceCompatibility = JavaVersion.${version.name}")
    }

    /**
     * Sets target compatibility
     */
    public fun targetCompatibility(version: JavaVersion) {
        line("targetCompatibility = JavaVersion.${version.name}")
    }

    /**
     * Configures java toolchain
     */
    public fun toolchain(init: JavaToolchainDsl.() -> Unit) {
        block("toolchain") {
            JavaToolchainDsl(this).apply(init)
        }
    }

    /**
     * Configures withJavadocJar
     */
    public fun withJavadocJar() {
        line("withJavadocJar()")
    }

    /**
     * Configures withSourcesJar
     */
    public fun withSourcesJar() {
        line("withSourcesJar()")
    }
}

/**
 * DSL for Java toolchain
 */
public class JavaToolchainDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the language version
     */
    public fun languageVersion(version: Int) {
        line("languageVersion = JavaLanguageVersion.of($version)")
    }

    /**
     * Sets the vendor
     */
    public fun vendor(vendor: String) {
        line("vendor = JvmVendorSpec.${vendor}")
    }
}