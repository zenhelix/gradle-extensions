package io.github.zenhelix.gradle.test.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PublishingDslTest {

    @Test
    fun `should handle cross-references between DSL components`() {
        val content = buildGradleKts {
            plugins {
                id("java-library")
                id("maven-publish")
                id("signing")
            }

            group("io.github.zenhelix")
            version("1.0.0")

            publishing {
                repositories {
                    mavenLocal()
                }

                publications {
                    mavenPublication("java") {
                        fromComponent("java")
                    }
                }
            }

            signing {
                useInMemoryPgpKeys("dummy-key", "dummy-password")
                sign(publishing.publications.asReference())
            }
        }

        assertThat(content).isEqualTo(
            """
            plugins {
                id("java-library")
                id("maven-publish")
                id("signing")
            }
            group = "io.github.zenhelix"
            version = "1.0.0"
            publishing {
                repositories {
                    mavenLocal()
                }
                publications {
                    create<MavenPublication>("java") {
                        from(components["java"])
                    }
                }
            }
            signing {
                useInMemoryPgpKeys("dummy-key", "dummy-password")
                sign(publishing.publications)
            }
            
        """.trimIndent()
        )
    }

    @Test
    fun `should handle withType for collections`() {
        val content = buildGradleKts {
            plugins {
                id("java-library")
                id("maven-publish")
            }

            group("io.github.zenhelix")
            version("1.0.0")

            publishing {
                publications {
                    mavenPublication("java") {
                        fromComponent("java")
                    }

                    withType("MavenPublication") {
                        pom {
                            name("Test Project")
                            description("A test project for Gradle DSL")
                            url("https://github.com/zenhelix/gradle-test-dsl")

                            licenses {
                                license {
                                    name("The Apache License, Version 2.0")
                                    url("https://www.apache.org/licenses/LICENSE-2.0.txt")
                                }
                            }

                            developers {
                                developer {
                                    id("zenhelix")
                                    name("Zen Helix")
                                    email("dev@zenhelix.io")
                                }
                            }
                        }
                    }
                }
            }
        }

        assertThat(content).isEqualTo(
            """
            plugins {
                id("java-library")
                id("maven-publish")
            }
            group = "io.github.zenhelix"
            version = "1.0.0"
            publishing {
                publications {
                    create<MavenPublication>("java") {
                        from(components["java"])
                    }
                    withType<MavenPublication> {
                        pom {
                            name = "Test Project"
                            description = "A test project for Gradle DSL"
                            url = "https://github.com/zenhelix/gradle-test-dsl"
                            licenses {
                                license {
                                    name = "The Apache License, Version 2.0"
                                    url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                                }
                            }
                            developers {
                                developer {
                                    id = "zenhelix"
                                    name = "Zen Helix"
                                    email = "dev@zenhelix.io"
                                }
                            }
                        }
                    }
                }
            }
            
        """.trimIndent()
        )
    }

    @Test
    fun `should handle complex cross-component DSL interactions`() {
        val content = buildGradleKts {
            plugins {
                id("org.jetbrains.kotlin.jvm", "1.9.0")
                id("maven-publish")
                id("signing")
            }

            group("io.github.zenhelix.test")
            version("0.1.0")

            repositories {
                mavenCentral()
            }

            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
                testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
            }

            java {
                withSourcesJar()
                withJavadocJar()
            }

            // Конфигурируем публикации
            publishing {
                repositories {
                    mavenLocal()

                    // Используем расширение, которое мы обсуждали
                    custom("mavenCentralPortal") {
                        block("credentials") {
                            line("username = \"dummy-user\"")
                            line("password = \"dummy-password\"")
                        }
                        block("uploader") {
                            block("aggregate") {
                                line("modules = true")
                            }
                        }
                    }
                }

                publications {
                    mavenPublication("library") {
                        fromComponent("java")

                        groupId("io.github.zenhelix.test")
                        artifactId("test-library")
                        version("0.1.0")
                    }
                }
            }

            signing {
                useInMemoryPgpKeys("dummy-key", "dummy-password")
                sign(publishing.publications.asReference())
            }

            // Добавление afterEvaluate блока, который работает с publications.withType
            afterEvaluate {
                publishing.publications.withType("MavenPublication") {
                    pom {
                        name("Test Library")
                        description("A test library for Gradle DSL")
                        url("https://github.com/zenhelix/gradle-test-dsl")

                        licenses {
                            license {
                                name("The Apache License, Version 2.0")
                                url("https://www.apache.org/licenses/LICENSE-2.0.txt")
                            }
                        }
                    }
                }
            }
        }

        assertThat(content).isEqualTo(
            """
            plugins {
                id("org.jetbrains.kotlin.jvm") version "1.9.0"
                id("maven-publish")
                id("signing")
            }
            group = "io.github.zenhelix.test"
            version = "0.1.0"
            repositories {
                mavenCentral()
            }
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
                testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
            }
            java {
                withSourcesJar()
                withJavadocJar()
            }
            publishing {
                repositories {
                    mavenLocal()
                    mavenCentralPortal {
                        credentials {
                            username = "dummy-user"
                            password = "dummy-password"
                        }
                        uploader {
                            aggregate {
                                modules = true
                            }
                        }
                    }
                }
                publications {
                    create<MavenPublication>("library") {
                        from(components["java"])
                        groupId = "io.github.zenhelix.test"
                        artifactId = "test-library"
                        version = "0.1.0"
                    }
                }
            }
            signing {
                useInMemoryPgpKeys("dummy-key", "dummy-password")
                sign(publications)
            }
            afterEvaluate {
                publishing.publications.withType<MavenPublication> {
                    pom {
                        name = "Test Library"
                        description = "A test library for Gradle DSL"
                        url = "https://github.com/zenhelix/gradle-test-dsl"
                        licenses {
                            license {
                                name = "The Apache License, Version 2.0"
                                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                            }
                        }
                    }
                }
            }
            
        """.trimIndent()
        )
    }
}