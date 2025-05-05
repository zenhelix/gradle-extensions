package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.DslReference

/**
 * DSL for signing
 */
public class SigningDsl(private val parent: GradleDsl) : GradleDsl by parent {
    override val dslPath: DslPath = parent.dslPath.append("signing")
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

    public fun sign(publicationsReference: DslReference<*>) {
        line("sign($publicationsReference)")
    }

}