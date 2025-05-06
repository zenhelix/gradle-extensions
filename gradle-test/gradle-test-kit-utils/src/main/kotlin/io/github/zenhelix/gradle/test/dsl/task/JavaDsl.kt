package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate
import org.gradle.api.JavaVersion

public class JavaDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var sourceCompatibility: JavaVersion by PropertyDelegate(parent) { "JavaVersion.${it.name}" }

    public var targetCompatibility: JavaVersion by PropertyDelegate(parent) { "JavaVersion.${it.name}" }

    public fun toolchain(init: JavaToolchainDsl.() -> Unit) {
        block("toolchain") {
            JavaToolchainDsl(this).apply(init)
        }
    }

    public fun withJavadocJar() {
        line("withJavadocJar()")
    }

    public fun withSourcesJar() {
        line("withSourcesJar()")
    }
}

public class JavaToolchainDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var languageVersion: Int by PropertyDelegate(parent) { "JavaLanguageVersion.of($it)" }

    public var vendor: String by PropertyDelegate(parent) { "JvmVendorSpec.${it}" }
}