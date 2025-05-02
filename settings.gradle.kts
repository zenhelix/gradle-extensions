@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "gradle-extensions"

project("gradle-extensions-platform") {
    include("gradle-extensions-platform-bom")
    include("gradle-extensions-platform-toml")
}
include("gradle-utils")
project("gradle-test") {
    include("gradle-test-all")
    include("gradle-test-kit-utils")
    include("gradle-assertions")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
}

private fun Settings.project(
    baseProject: String, initializer: IncludeContext.() -> Unit = {}
): IncludeContext = IncludeContext(baseProject, this).apply(initializer)

private class IncludeContext(private val baseProject: String, private val delegate: Settings) {

    fun project(
        baseProject: String, initializer: IncludeContext.() -> Unit = {}
    ): IncludeContext = IncludeContext("${this.baseProject}:$baseProject", this.delegate).apply(initializer)

    fun include(vararg project: String) {
        project.forEach {
            delegate.include("$baseProject:$it")
        }
    }

}

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenLocal()
    }

    val zenhelixGradleVersion: String by settings
    val mavenCentralPublishVersion: String by settings

    plugins {
        id("io.github.zenhelix.maven-central-publish") version mavenCentralPublishVersion
        id("io.github.zenhelix.kotlin-jvm-library") version zenhelixGradleVersion
        id("io.github.zenhelix.jdk17.convention") version zenhelixGradleVersion
    }
}