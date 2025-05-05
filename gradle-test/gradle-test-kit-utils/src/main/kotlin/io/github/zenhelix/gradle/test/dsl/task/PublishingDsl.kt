package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.PropertyDelegate
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractPolymorphicDomainObjectContainerDsl
import io.github.zenhelix.gradle.test.dsl.gradle.RepositoryHandlerDsl

/**
 * DSL for publishing block
 */
public class PublishingDsl(private val parent: GradleDsl) : GradleDsl by parent {
    override val dslPath: DslPath = parent.dslPath.append("publishing")

    private val publicationsDsl = PublicationsDsl(this)

    /**
     * Access to publications
     */
    public val publications: PublicationsDsl
        get() = publicationsDsl

    /**
     * Configures repositories for publications
     */
    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    /**
     * Configures publications
     */
    public fun publications(init: PublicationsDsl.() -> Unit) {
        block("publications") {
            withDsl(publicationsDsl, init)
        }
    }
}

/**
 * Marker interface for publications
 */
public interface Publication

/**
 * DSL for publications
 */
public class PublicationsDsl(
    override val parent: GradleDsl
) : AbstractPolymorphicDomainObjectContainerDsl<Publication, MavenPublicationDsl>(parent, "publications") {
    override val dslPath: DslPath = parent.dslPath.append("publications")

    /**
     * Creates configurator for publication
     */
    override fun createConfigurator(dsl: GradleDsl): MavenPublicationDsl = MavenPublicationDsl(dsl)

    /**
     * Creates Maven publication
     */
    public fun mavenPublication(name: String, init: MavenPublicationDsl.() -> Unit) {
        create<Publication>("MavenPublication", name, init)
    }
}

/**
 * DSL for Maven publication
 */
public class MavenPublicationDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Group ID property
     */
    public var groupId: String by PropertyDelegate(parent)

    /**
     * Artifact ID property
     */
    public var artifactId: String by PropertyDelegate(parent)

    /**
     * Version property
     */
    public var version: String by PropertyDelegate(parent)

    public fun fromComponent(vararg component: String) {
        val components = component.joinToString("\", \"", "\"", "\"")
        line("from(components[$components])")
    }

    /**
     * Configures POM
     */
    public fun pom(init: PomDsl.() -> Unit) {
        block("pom") {
            PomDsl(this).apply(init)
        }
    }
}

/**
 * DSL for POM
 */
public class PomDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Description property
     */
    public var description: String by PropertyDelegate(parent)

    /**
     * URL property
     */
    public var url: String by PropertyDelegate(parent)

    /**
     * Name property
     */
    public var name: String by PropertyDelegate(parent)

    /**
     * Configures licenses
     */
    public fun licenses(init: LicensesDsl.() -> Unit) {
        block("licenses") {
            LicensesDsl(this).apply(init)
        }
    }

    /**
     * Configures SCM
     */
    public fun scm(init: ScmDsl.() -> Unit) {
        block("scm") {
            ScmDsl(this).apply(init)
        }
    }

    /**
     * Configures developers
     */
    public fun developers(init: DevelopersDsl.() -> Unit) {
        block("developers") {
            DevelopersDsl(this).apply(init)
        }
    }

    /**
     * Configures issue management
     */
    public fun issueManagement(init: IssueManagementDsl.() -> Unit) {
        block("issueManagement") {
            IssueManagementDsl(this).apply(init)
        }
    }
}

/**
 * DSL for issue management
 */
public class IssueManagementDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * System property
     */
    public var system: String by PropertyDelegate(parent)

    /**
     * URL property
     */
    public var url: String by PropertyDelegate(parent)
}

/**
 * DSL for licenses
 */
public class LicensesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a license
     */
    public fun license(init: LicenseDsl.() -> Unit) {
        block("license") {
            LicenseDsl(this).apply(init)
        }
    }

    /**
     * Adds Apache 2.0 license
     */
    public fun apache2() {
        license {
            name = "The Apache License, Version 2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
        }
    }

    /**
     * Adds MIT license
     */
    public fun mit() {
        license {
            name = "MIT License"
            url = "https://opensource.org/licenses/MIT"
        }
    }

    /**
     * Adds GPL v3 license
     */
    public fun gpl3() {
        license {
            name = "GNU General Public License v3.0"
            url = "https://www.gnu.org/licenses/gpl-3.0.txt"
        }
    }

    /**
     * Adds LGPL v3 license
     */
    public fun lgpl3() {
        license {
            name = "GNU Lesser General Public License v3.0"
            url = "https://www.gnu.org/licenses/lgpl-3.0.txt"
        }
    }

    /**
     * Adds BSD 3-Clause license
     */
    public fun bsd3Clause() {
        license {
            name = "BSD 3-Clause License"
            url = "https://opensource.org/licenses/BSD-3-Clause"
        }
    }
}

/**
 * DSL for license
 */
public class LicenseDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Name property
     */
    public var name: String by PropertyDelegate(parent)

    /**
     * URL property
     */
    public var url: String by PropertyDelegate(parent)

    /**
     * Distribution property
     */
    public var distribution: String by PropertyDelegate(parent)

    /**
     * Comments property
     */
    public var comments: String by PropertyDelegate(parent)
}

/**
 * DSL for SCM
 */
public class ScmDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Connection property
     */
    public var connection: String by PropertyDelegate(parent)

    /**
     * Developer connection property
     */
    public var developerConnection: String by PropertyDelegate(parent)

    /**
     * URL property
     */
    public var url: String by PropertyDelegate(parent)

    /**
     * Tag property
     */
    public var tag: String by PropertyDelegate(parent)

}

/**
 * DSL for developers
 */
public class DevelopersDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds a developer
     */
    public fun developer(init: DeveloperDsl.() -> Unit) {
        block("developer") {
            DeveloperDsl(this).apply(init)
        }
    }
}

/**
 * DSL for developer
 */
public class DeveloperDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * ID property
     */
    public var id: String by PropertyDelegate(parent)

    /**
     * Name property
     */
    public var name: String by PropertyDelegate(parent)

    /**
     * Email property
     */
    public var email: String by PropertyDelegate(parent)

    /**
     * URL property
     */
    public var url: String by PropertyDelegate(parent)

    /**
     * Organization property
     */
    public var organization: String by PropertyDelegate(parent)

    /**
     * Organization URL property
     */
    public var organizationUrl: String by PropertyDelegate(parent)

    /**
     * Sets the developer roles
     */
    public fun roles(vararg roles: String) {
        if (roles.isNotEmpty()) {
            val rolesStr = roles.joinToString("\", \"", "\"", "\"")
            line("roles = setOf($rolesStr)")
        }
    }

    /**
     * Timezone property
     */
    public var timezone: String by PropertyDelegate(parent)
}