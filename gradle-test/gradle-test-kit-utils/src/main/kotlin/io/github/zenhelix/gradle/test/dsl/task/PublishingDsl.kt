package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractPolymorphicDomainObjectContainerDsl
import io.github.zenhelix.gradle.test.dsl.gradle.RepositoryHandlerDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate

public class PublishingDsl(private val parent: GradleDsl) : GradleDsl by parent {
    override val dslPath: DslPath = parent.dslPath.append("publishing")

    private val publicationsDsl = PublicationsDsl(this)

    public val publications: PublicationsDsl
        get() = publicationsDsl

    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    public fun publications(init: PublicationsDsl.() -> Unit) {
        block("publications") {
            withDsl(this@PublishingDsl.publicationsDsl, init)
        }
    }
}

public interface Publication

public class PublicationsDsl(
    override val parent: GradleDsl
) : AbstractPolymorphicDomainObjectContainerDsl<Publication, MavenPublicationDsl>(parent, "publications") {
    override val dslPath: DslPath = parent.dslPath.append("publications")

    override fun createConfigurator(dsl: GradleDsl): MavenPublicationDsl = MavenPublicationDsl(dsl)

    public fun mavenPublication(name: String, init: MavenPublicationDsl.() -> Unit) {
        create<Publication>("MavenPublication", name, init)
    }
}

public class MavenPublicationDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var groupId: String by PropertyDelegate(parent)

    public var artifactId: String by PropertyDelegate(parent)

    public var version: String by PropertyDelegate(parent)

    public fun fromComponent(vararg component: String) {
        val components = component.joinToString("\", \"", "\"", "\"")
        line("from(components[$components])")
    }

    public fun pom(init: PomDsl.() -> Unit) {
        block("pom") {
            PomDsl(this).apply(init)
        }
    }
}

public class PomDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var description: String by PropertyDelegate(parent)

    public var url: String by PropertyDelegate(parent)

    public var name: String by PropertyDelegate(parent)

    public fun licenses(init: LicensesDsl.() -> Unit) {
        block("licenses") {
            LicensesDsl(this).apply(init)
        }
    }

    public fun scm(init: ScmDsl.() -> Unit) {
        block("scm") {
            ScmDsl(this).apply(init)
        }
    }

    public fun developers(init: DevelopersDsl.() -> Unit) {
        block("developers") {
            DevelopersDsl(this).apply(init)
        }
    }

    public fun issueManagement(init: IssueManagementDsl.() -> Unit) {
        block("issueManagement") {
            IssueManagementDsl(this).apply(init)
        }
    }
}

public class IssueManagementDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var system: String by PropertyDelegate(parent)

    public var url: String by PropertyDelegate(parent)
}

public class LicensesDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun license(init: LicenseDsl.() -> Unit) {
        block("license") {
            LicenseDsl(this).apply(init)
        }
    }

    public fun apache2() {
        license {
            name = "The Apache License, Version 2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
        }
    }

    public fun mit() {
        license {
            name = "MIT License"
            url = "https://opensource.org/licenses/MIT"
        }
    }

    public fun gpl3() {
        license {
            name = "GNU General Public License v3.0"
            url = "https://www.gnu.org/licenses/gpl-3.0.txt"
        }
    }

    public fun lgpl3() {
        license {
            name = "GNU Lesser General Public License v3.0"
            url = "https://www.gnu.org/licenses/lgpl-3.0.txt"
        }
    }

    public fun bsd3Clause() {
        license {
            name = "BSD 3-Clause License"
            url = "https://opensource.org/licenses/BSD-3-Clause"
        }
    }
}

public class LicenseDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var name: String by PropertyDelegate(parent)

    public var url: String by PropertyDelegate(parent)

    public var distribution: String by PropertyDelegate(parent)

    public var comments: String by PropertyDelegate(parent)
}

public class ScmDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var connection: String by PropertyDelegate(parent)

    public var developerConnection: String by PropertyDelegate(parent)

    public var url: String by PropertyDelegate(parent)

    public var tag: String by PropertyDelegate(parent)

}

public class DevelopersDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun developer(init: DeveloperDsl.() -> Unit) {
        block("developer") {
            DeveloperDsl(this).apply(init)
        }
    }
}

public class DeveloperDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var id: String by PropertyDelegate(parent)

    public var name: String by PropertyDelegate(parent)

    public var email: String by PropertyDelegate(parent)

    public var url: String by PropertyDelegate(parent)

    public var organization: String by PropertyDelegate(parent)

    public var organizationUrl: String by PropertyDelegate(parent)

    public fun roles(vararg roles: String) {
        if (roles.isNotEmpty()) {
            val rolesStr = roles.joinToString("\", \"", "\"", "\"")
            line("roles = setOf($rolesStr)")
        }
    }

    public var timezone: String by PropertyDelegate(parent)
}