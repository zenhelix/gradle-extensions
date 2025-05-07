package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.task.MavenPublication
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

            group = "io.github.zenhelix"
            version = "1.0.0"

            publishing {
                repositories {
                    mavenLocal()
                }

                publications {
                    create<MavenPublication>("java") {
                        fromComponent("java")
                    }
                }
            }

            signing {
                useInMemoryPgpKeys("dummy-key", "dummy-password")
                sign(publishing.publications)
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

            group = "io.github.zenhelix"
            version = "1.0.0"

            publishing {
                publications {
                    create<MavenPublication>("java") {
                        fromComponent("java")
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
    fun `should handle publications withType reference outside publishing block`() {
        val content = buildGradleKts {
            plugins {
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
                        fromComponent("java")
                    }
                }
            }

            signing {
                useInMemoryPgpKeys("dummy-key", "dummy-password")
                sign(publishing.publications)
            }

            publishing.publications.withType<MavenPublication> {
                pom {
                    description = "Test description"
                    url = "https://example.com"
                    licenses {
                        apache2()
                    }
                    developers {
                        developer {
                            id = "test"
                            name = "Test User"
                            email = "test@example.com"
                        }
                    }
                }
            }
        }

        assertThat(content).isEqualTo(
            """
        plugins {
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
        publishing.publications.withType<MavenPublication> {
            pom {
                description = "Test description"
                url = "https://example.com"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "test"
                        name = "Test User"
                        email = "test@example.com"
                    }
                }
            }
        }
        
        """.trimIndent()
        )
    }
}