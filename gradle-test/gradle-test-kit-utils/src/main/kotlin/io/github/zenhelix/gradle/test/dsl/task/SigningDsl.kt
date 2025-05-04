package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * DSL for signing
 */
public class SigningDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Uses in-memory PGP keys
     */
    public fun useInMemoryPgpKeys(key: String, password: String) {
        line("useInMemoryPgpKeys(\"$key\", \"$password\")")
    }

    /**
     * Uses GPG command line
     */
    public fun useGpgCmd() {
        line("useGpgCmd()")
    }

    /**
     * Sets the sign required property
     */
    public fun setRequired(value: Boolean) {
        line("isRequired = $value")
    }

}