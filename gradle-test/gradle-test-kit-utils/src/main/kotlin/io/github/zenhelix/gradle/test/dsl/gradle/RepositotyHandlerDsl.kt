package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

public class RepositoryHandlerDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun mavenLocal() {
        line("mavenLocal()")
    }

    public fun mavenCentral() {
        line("mavenCentral()")
    }

    public fun google() {
        line("google()")
    }

    public fun gradlePluginPortal() {
        line("gradlePluginPortal()")
    }

    public fun maven(url: String, init: MavenRepositoryDsl.() -> Unit = {}) {
        block("maven") {
            line("url = uri(${formatValue(url)})")
            MavenRepositoryDsl(this).apply(init)
        }
    }

    public fun maven(name: String, url: String, init: MavenRepositoryDsl.() -> Unit = {}) {
        block("maven") {
            line("name = ${formatValue(name)}")
            line("url = uri(${formatValue(url)})")
            MavenRepositoryDsl(this).apply(init)
        }
    }

    public fun ivy(url: String, init: IvyRepositoryDsl.() -> Unit = {}) {
        block("ivy") {
            line("url = uri(${formatValue(url)})")
            IvyRepositoryDsl(this).apply(init)
        }
    }
}

public open class RepositoryDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun credentials(init: CredentialsDsl.() -> Unit) {
        block("credentials") {
            CredentialsDsl(this).apply(init)
        }
    }
}

public class CredentialsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun username(value: String) {
        line("username = ${formatValue(value)}")
    }

    public fun password(value: String) {
        line("password = ${formatValue(value)}")
    }
}

public class MavenRepositoryDsl(parent: GradleDsl) : RepositoryDsl(parent) {

    public fun authentication(init: MavenAuthenticationDsl.() -> Unit) {
        block("authentication") {
            MavenAuthenticationDsl(this).apply(init)
        }
    }

    public fun content(init: MavenContentDsl.() -> Unit) {
        block("content") {
            MavenContentDsl(this).apply(init)
        }
    }
}

public class MavenAuthenticationDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun basic(name: String = "basic") {
        line("create(${formatValue(name)}, BasicAuthentication::class.java)")
    }

    public fun digest(name: String = "digest") {
        line("create(${formatValue(name)}, DigestAuthentication::class.java)")
    }
}

public class MavenContentDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun includeGroup(group: String) {
        line("includeGroup(${formatValue(group)})")
    }

    public fun includeGroups(vararg groups: String) {
        groups.forEach { group ->
            line("includeGroup(${formatValue(group)})")
        }
    }

    public fun includeModule(group: String, module: String) {
        line("includeModule(${formatValue(group)}, ${formatValue(module)})")
    }

    public fun excludeGroup(group: String) {
        line("excludeGroup(${formatValue(group)})")
    }

    public fun excludeModule(group: String, module: String) {
        line("excludeModule(${formatValue(group)}, ${formatValue(module)})")
    }
}

public class IvyRepositoryDsl(parent: GradleDsl) : RepositoryDsl(parent) {

    public fun patternLayout(init: IvyPatternLayoutDsl.() -> Unit) {
        block("patternLayout") {
            IvyPatternLayoutDsl(this).apply(init)
        }
    }

    public fun metadataSources(init: IvyMetadataSourcesDsl.() -> Unit) {
        block("metadataSources") {
            IvyMetadataSourcesDsl(this).apply(init)
        }
    }
}

public class IvyPatternLayoutDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun artifact(pattern: String) {
        line("artifact(${formatValue(pattern)})")
    }

    public fun ivy(pattern: String) {
        line("ivy(${formatValue(pattern)})")
    }
}

public class IvyMetadataSourcesDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun artifact() {
        line("artifact()")
    }

    public fun ivyDescriptor() {
        line("ivyDescriptor()")
    }

    public fun gradleMetadata() {
        line("gradleMetadata()")
    }
}