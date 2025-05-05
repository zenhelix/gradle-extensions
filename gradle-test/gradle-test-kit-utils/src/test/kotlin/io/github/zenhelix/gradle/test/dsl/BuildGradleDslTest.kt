package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.task.kotlin.KotlinJvmTarget
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BuildGradleDslTest {

    @Test
    fun `should create build gradle with plugins`() {

        val content = buildGradleKts {
            plugins {
                id("io.github.zenhelix.test")
                builtin("java-library")
            }

            allprojects {
                group("test.zenhelix")
                version("0.1.0")
            }
        }

        assertThat(content).isEqualTo("""
            plugins {
                id("io.github.zenhelix.test")
                `java-library`
            }
            allprojects {
                group = "test.zenhelix"
                version = "0.1.0"
            }
            
        """.trimIndent())
    }

    @Test
    fun `should create build gradle with maven publishing`() {

        val content = buildGradleKts {
            plugins {
                id("io.github.zenhelix.test")
            }

            publishing {
                repositories {
                    mavenLocal()
                }

                publications {
                    mavenPublication("javaLib") {
                        fromComponent("java")
                        pom {
                            description("Test library")
                            licenses {
                                apache2()
                            }
                        }
                    }
                }
            }
        }

        assertThat(content).isEqualTo("""
            plugins {
                id("io.github.zenhelix.test")
            }
            publishing {
                repositories {
                    mavenLocal()
                }
                publications {
                    create<MavenPublication>("javaLib") {
                        from(components["java"])
                        pom {
                            description = "Test library"
                            licenses {
                                license {
                                    name = "The Apache License, Version 2.0"
                                    url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                                }
                            }
                        }
                    }
                }
            }
            
        """.trimIndent())
    }

    @Test
    fun `should create build gradle with kotlin multiplatform`() {

        val content = buildGradleKts {
            plugins {
                id("org.jetbrains.kotlin.multiplatform", "2.1.0")
            }

            kotlinMultiplatform {
                jvm {
                    compilerOptions {
                        jvmTarget(KotlinJvmTarget.JVM_17)
                    }
                }
                linuxX64()

                sourceSets {
                    commonMain {
                        kotlin("src/commonMain/kotlin")
                        dependencies {
                            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                        }
                    }
                }
            }
        }

        assertThat(content).isEqualTo("""
            plugins {
                id("org.jetbrains.kotlin.multiplatform") version "2.1.0"
            }
            kotlin {
                jvm() {
                    compilerOptions {
                        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
                    }
                }
                linuxX64()
                sourceSets {
                    val commonMain by getting {
                        kotlin.srcDir("src/commonMain/kotlin")
                        dependencies {
                            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                        }
                    }
                }
            }
            
        """.trimIndent())
    }

    @Test
    fun `should create build gradle with android configuration`() {

        val content = buildGradleKts {
            plugins {
                id("com.android.library", "8.2.0")
            }

            androidLibrary {
                namespace = "com.example.app"
                compileSdk = 34
                defaultConfig {
                    minSdk = 24
                    targetSdk = 34
                }
                buildTypes {
                    release {
                        minifyEnabled(true)
                    }
                }
            }
        }

        assertThat(content).isEqualTo("""
            plugins {
                id("com.android.library") version "8.2.0"
            }
            android {
                namespace = "com.example.app"
                compileSdk = 34
                defaultConfig {
                    minSdk = 24
                    targetSdk = 34
                }
                buildTypes {
                    release {
                        isMinifyEnabled = true
                    }
                }
            }
            
        """.trimIndent())
    }

    @Test
    fun `should create build gradle with task`() {

        val content = buildGradleKts {
            task("createTestFile") {
                group("build")
                description("Creates a test file")
                doLast {
                    file("src/main/resources/test.txt") {
                        mkdirs()
                        writeText("""
                        Hello, World!
                        This is a test file.
                        """)
                    }
                }
            }
        }

        assertThat(content).isEqualTo("""
            tasks.register("createTestFile") {
                group = "build"
                description = "Creates a test file"
                doLast {
                    file("src/main/resources/test.txt").apply {
                        parentFile.mkdirs()
                        writeText(""${'"'}
                                    Hello, World!
                                    This is a test file.
                                    ""${'"'})
                    }
                }
            }
            
        """.trimIndent())
    }
}