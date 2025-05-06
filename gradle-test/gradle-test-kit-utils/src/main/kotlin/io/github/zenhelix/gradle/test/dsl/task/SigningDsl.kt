package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.NamedDomainObjectCollectionDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate

/**
 * DSL for signing
 */
public class SigningDsl(private val parent: GradleDsl) : GradleDsl by parent {
    override val dslPath: DslPath = parent.dslPath.append("signing")

    /**
     * Uses in-memory PGP keys
     */
    public fun useInMemoryPgpKeys(key: String, password: String) {
        line("useInMemoryPgpKeys(${formatValue(key)}, ${formatValue(password)})")
    }

    /**
     * Uses GPG command line
     */
    public fun useGpgCmd() {
        line("useGpgCmd()")
    }

    /**
     * Required property
     */
    public var isRequired: Boolean by PropertyDelegate(parent)

    public fun sign(publicationsReference: NamedDomainObjectCollectionDsl<*, *>) {
        line("sign(${publicationsReference.asReference()})")
    }
}