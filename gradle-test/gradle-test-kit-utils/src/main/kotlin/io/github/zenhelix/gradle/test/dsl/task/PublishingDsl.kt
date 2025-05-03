package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * DSL for publishing block
 */
public class PublishingDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures repositories for publishing
     */
    public fun repositories(init: PublishingRepositoriesDsl.() -> Unit) {
        block("repositories") {
            PublishingRepositoriesDsl(this).apply(init)
        }
    }

    /**
     * Configures publications
     */
    public fun publications(init: PublicationsDsl.() -> Unit) {
        block("publications") {
            PublicationsDsl(this).apply(init)
        }
    }
}

/**
 * DSL for repositories in publishing
 */
public class PublishingRepositoriesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds local Maven repository
     */
    public fun mavenLocal() {
        line("mavenLocal()")
    }

    /**
     * Adds central Maven repository
     */
    public fun mavenCentral() {
        line("mavenCentral()")
    }

}

/**
 * DSL for aggregation
 */
public class AggregateDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Enables/disables module aggregation
     */
    public fun modules(value: Boolean) {
        line("modules = $value")
    }

    /**
     * Enables/disables module publications aggregation
     */
    public fun modulePublications(value: Boolean) {
        line("modulePublications = $value")
    }
}

/**
 * DSL for publications
 */
public class PublicationsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Creates a Maven publication
     */
    public fun mavenPublication(name: String, init: MavenPublicationDsl.() -> Unit) {
        block("create<MavenPublication>(\"$name\")") {
            MavenPublicationDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Maven publication
 */
public class MavenPublicationDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the publication source
     */
    public fun from(component: String) {
        line("from(components[\"$component\"])")
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
}

/**
 * DSL for signing
 */
public class SigningDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Uses in-memory PGP keys
     */
    public fun useInMemoryPgpKeys(key: String, password: String) {
        line("useInMemoryPgpKeys(\"\"\"$key\"\"\", \"$password\")")
    }

    /**
     * Signs publications
     */
    public fun sign(publications: String = "publishing.publications") {
        line("sign($publications)")
    }
}