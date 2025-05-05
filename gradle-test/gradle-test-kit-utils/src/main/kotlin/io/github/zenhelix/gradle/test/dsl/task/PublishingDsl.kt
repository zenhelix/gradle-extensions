package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.gradle.AbstractPolymorphicDomainObjectContainerDsl
import io.github.zenhelix.gradle.test.dsl.gradle.RepositoryHandlerDsl

/**
 * DSL for publishing block
 */
public class PublishingDsl(private val parent: GradleDsl) : GradleDsl by parent {
    override val dslPath: DslPath = parent.dslPath.append("publishing")

    private val publicationsDsl = PublicationsDsl(this)

    /**
     * Доступ к публикациям
     */
    public val publications: PublicationsDsl
        get() = publicationsDsl

    /**
     * Конфигурирует репозитории для публикаций
     */
    public fun repositories(init: RepositoryHandlerDsl.() -> Unit) {
        block("repositories") {
            RepositoryHandlerDsl(this).apply(init)
        }
    }

    /**
     * Конфигурирует публикации
     */
    public fun publications(init: PublicationsDsl.() -> Unit) {
        block("publications") {
            withDsl(publicationsDsl, init)
        }
    }
}

/**
 * Маркерный интерфейс для публикаций
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
     * Создает конфигуратор для публикации
     */
    override fun createConfigurator(dsl: GradleDsl): MavenPublicationDsl = MavenPublicationDsl(dsl)

    /**
     * Создает Maven публикацию
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
     * Sets the groupId explicitly
     */
    public fun groupId(value: String) {
        line("groupId = \"$value\"")
    }

    /**
     * Sets the artifactId explicitly
     */
    public fun artifactId(value: String) {
        line("artifactId = \"$value\"")
    }

    /**
     * Sets the version explicitly
     */
    public fun version(value: String) {
        line("version = \"$value\"")
    }

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
     * Sets the description
     */
    public fun description(value: String) {
        line("description = \"$value\"")
    }

    /**
     * Sets the URL
     */
    public fun url(value: String) {
        line("url = \"$value\"")
    }

    /**
     * Sets the name
     */
    public fun name(value: String) {
        line("name = \"$value\"")
    }

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
     * Sets the system
     */
    public fun system(value: String) {
        line("system = \"$value\"")
    }

    /**
     * Sets the URL
     */
    public fun url(value: String) {
        line("url = \"$value\"")
    }
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
            name("The Apache License, Version 2.0")
            url("https://www.apache.org/licenses/LICENSE-2.0.txt")
        }
    }

    /**
     * Adds MIT license
     */
    public fun mit() {
        license {
            name("MIT License")
            url("https://opensource.org/licenses/MIT")
        }
    }

    /**
     * Adds GPL v3 license
     */
    public fun gpl3() {
        license {
            name("GNU General Public License v3.0")
            url("https://www.gnu.org/licenses/gpl-3.0.txt")
        }
    }

    /**
     * Adds LGPL v3 license
     */
    public fun lgpl3() {
        license {
            name("GNU Lesser General Public License v3.0")
            url("https://www.gnu.org/licenses/lgpl-3.0.txt")
        }
    }

    /**
     * Adds BSD 3-Clause license
     */
    public fun bsd3Clause() {
        license {
            name("BSD 3-Clause License")
            url("https://opensource.org/licenses/BSD-3-Clause")
        }
    }
}

/**
 * DSL for license
 */
public class LicenseDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the license name
     */
    public fun name(value: String) {
        line("name = \"$value\"")
    }

    /**
     * Sets the license URL
     */
    public fun url(value: String) {
        line("url = \"$value\"")
    }

    /**
     * Sets the license distribution
     */
    public fun distribution(value: String) {
        line("distribution = \"$value\"")
    }

    /**
     * Sets the license comments
     */
    public fun comments(value: String) {
        line("comments = \"$value\"")
    }
}

/**
 * DSL for SCM
 */
public class ScmDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the connection
     */
    public fun connection(value: String) {
        line("connection = \"$value\"")
    }

    /**
     * Sets the developer connection
     */
    public fun developerConnection(value: String) {
        line("developerConnection = \"$value\"")
    }

    /**
     * Sets the URL
     */
    public fun url(value: String) {
        line("url = \"$value\"")
    }

    /**
     * Sets the tag
     */
    public fun tag(value: String) {
        line("tag = \"$value\"")
    }

    /**
     * Configures Git SCM
     */
    public fun git(repoUrl: String) {
        connection("scm:git:git://$repoUrl.git")
        developerConnection("scm:git:ssh://$repoUrl.git")
        url("https://$repoUrl")
    }
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
     * Sets the developer ID
     */
    public fun id(value: String) {
        line("id = \"$value\"")
    }

    /**
     * Sets the developer name
     */
    public fun name(value: String) {
        line("name = \"$value\"")
    }

    /**
     * Sets the developer email
     */
    public fun email(value: String) {
        line("email = \"$value\"")
    }

    /**
     * Sets the developer URL
     */
    public fun url(value: String) {
        line("url = \"$value\"")
    }

    /**
     * Sets the developer organization
     */
    public fun organization(value: String) {
        line("organization = \"$value\"")
    }

    /**
     * Sets the developer organization URL
     */
    public fun organizationUrl(value: String) {
        line("organizationUrl = \"$value\"")
    }

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
     * Sets the developer timezone
     */
    public fun timezone(value: String) {
        line("timezone = \"$value\"")
    }
}