package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * DSL for repositories in publishing
 */
public class RepositoryHandlerDsl(private val parent: GradleDsl) : GradleDsl by parent {
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

    /**
     * Adds Google repository
     */
    public fun google() {
        line("google()")
    }

    /**
     * Adds gradlePluginPortal repository
     */
    public fun gradlePluginPortal() {
        line("gradlePluginPortal()")
    }

    /**
     * Adds Maven repository with URL
     */
    public fun maven(url: String, init: MavenRepositoryDsl.() -> Unit = {}) {
        block("maven") {
            line("url = uri(${formatValue(url)})")
            MavenRepositoryDsl(this).apply(init)
        }
    }

    /**
     * Adds Maven repository with named URL
     */
    public fun maven(name: String, url: String, init: MavenRepositoryDsl.() -> Unit = {}) {
        block("maven") {
            line("name = ${formatValue(name)}")
            line("url = uri(${formatValue(url)})")
            MavenRepositoryDsl(this).apply(init)
        }
    }

    /**
     * Adds Ivy repository with URL
     */
    public fun ivy(url: String, init: IvyRepositoryDsl.() -> Unit = {}) {
        block("ivy") {
            line("url = uri(${formatValue(url)})")
            IvyRepositoryDsl(this).apply(init)
        }
    }
}

/**
 * Base repository DSL - simplified hierarchy
 */
public open class RepositoryDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Configures repository credentials
     */
    public fun credentials(init: CredentialsDsl.() -> Unit) {
        block("credentials") {
            CredentialsDsl(this).apply(init)
        }
    }
}

/**
 * DSL for credentials
 */
public class CredentialsDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Sets the username
     */
    public fun username(value: String) {
        line("username = ${formatValue(value)}")
    }

    /**
     * Sets the password
     */
    public fun password(value: String) {
        line("password = ${formatValue(value)}")
    }
}

/**
 * DSL for Maven repository
 */
public class MavenRepositoryDsl(parent: GradleDsl) : RepositoryDsl(parent) {
    /**
     * Configures authentication
     */
    public fun authentication(init: MavenAuthenticationDsl.() -> Unit) {
        block("authentication") {
            MavenAuthenticationDsl(this).apply(init)
        }
    }

    /**
     * Configures content
     */
    public fun content(init: MavenContentDsl.() -> Unit) {
        block("content") {
            MavenContentDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Maven authentication
 */
public class MavenAuthenticationDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Creates basic authentication
     */
    public fun basic(name: String = "basic") {
        line("create(${formatValue(name)}, BasicAuthentication::class.java)")
    }

    /**
     * Creates digest authentication
     */
    public fun digest(name: String = "digest") {
        line("create(${formatValue(name)}, DigestAuthentication::class.java)")
    }
}

/**
 * DSL for Maven content
 */
public class MavenContentDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Includes group
     */
    public fun includeGroup(group: String) {
        line("includeGroup(${formatValue(group)})")
    }

    /**
     * Includes groups
     */
    public fun includeGroups(vararg groups: String) {
        groups.forEach { group ->
            line("includeGroup(${formatValue(group)})")
        }
    }

    /**
     * Includes module
     */
    public fun includeModule(group: String, module: String) {
        line("includeModule(${formatValue(group)}, ${formatValue(module)})")
    }

    /**
     * Excludes group
     */
    public fun excludeGroup(group: String) {
        line("excludeGroup(${formatValue(group)})")
    }

    /**
     * Excludes module
     */
    public fun excludeModule(group: String, module: String) {
        line("excludeModule(${formatValue(group)}, ${formatValue(module)})")
    }
}

/**
 * DSL for Ivy repository
 */
public class IvyRepositoryDsl(parent: GradleDsl) : RepositoryDsl(parent) {
    /**
     * Sets the pattern layout
     */
    public fun patternLayout(init: IvyPatternLayoutDsl.() -> Unit) {
        block("patternLayout") {
            IvyPatternLayoutDsl(this).apply(init)
        }
    }

    /**
     * Sets the metadata sources
     */
    public fun metadataSources(init: IvyMetadataSourcesDsl.() -> Unit) {
        block("metadataSources") {
            IvyMetadataSourcesDsl(this).apply(init)
        }
    }
}

/**
 * DSL for Ivy pattern layout
 */
public class IvyPatternLayoutDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Adds an artifact pattern
     */
    public fun artifact(pattern: String) {
        line("artifact(${formatValue(pattern)})")
    }

    /**
     * Adds an ivy pattern
     */
    public fun ivy(pattern: String) {
        line("ivy(${formatValue(pattern)})")
    }
}

/**
 * DSL for Ivy metadata sources
 */
public class IvyMetadataSourcesDsl(private val parent: GradleDsl) : GradleDsl by parent {
    /**
     * Uses artifact metadata
     */
    public fun artifact() {
        line("artifact()")
    }

    /**
     * Uses ivy descriptor metadata
     */
    public fun ivyDescriptor() {
        line("ivyDescriptor()")
    }

    /**
     * Enables gradle metadata
     */
    public fun gradleMetadata() {
        line("gradleMetadata()")
    }
}