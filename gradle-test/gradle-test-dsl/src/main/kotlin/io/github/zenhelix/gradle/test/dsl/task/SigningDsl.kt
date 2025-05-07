package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.NamedDomainObjectCollectionDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate

public class SigningDsl(private val parent: GradleDsl) : GradleDsl by parent {
    override val dslPath: DslPath = parent.dslPath.append("signing")

    public fun useInMemoryPgpKeys(key: String, password: String) {
        line("useInMemoryPgpKeys(${formatValue(key)}, ${formatValue(password)})")
    }

    public fun useGpgCmd() {
        line("useGpgCmd()")
    }

    public var isRequired: Boolean by PropertyDelegate(parent)

    public fun sign(publicationsReference: NamedDomainObjectCollectionDsl<*, *>) {
        line("sign(${publicationsReference.asReference()})")
    }
}