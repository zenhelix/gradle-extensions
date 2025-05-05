package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.PropertyDelegate
import org.gradle.api.JavaVersion

/**
 * DSL for Java
 */
public class JavaDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Source compatibility property
     */
    public var sourceCompatibility: JavaVersion by PropertyDelegate(parent) { "JavaVersion.${it.name}" }

    /**
     * Target compatibility property
     */
    public var targetCompatibility: JavaVersion by PropertyDelegate(parent) { "JavaVersion.${it.name}" }

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
     * Language version property
     */
    public var languageVersion: Int by PropertyDelegate(parent) { "JavaLanguageVersion.of($it)" }

    /**
     * Vendor property
     */
    public var vendor: String by PropertyDelegate(parent) { "JvmVendorSpec.${it}" }
}