package io.github.zenhelix.gradle.test.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SettingsGradleDslTest {

    @Test
    fun `should create settings gradle with modules`() {

        val content = settingsGradleKts {
            rootProjectName("test-project")
            include("module1", "module2")

            dependencyResolutionManagement {
                repositories {
                    mavenCentral()
                    google()
                }
            }
        }


        assertThat(content).isEqualTo("""
            rootProject.name = "test-project"
            
            include(
                ":module1",
                ":module2"
            )
            
            dependencyResolutionManagement {
                repositories {
                    mavenCentral()
                    google()
                }
            }


        """.trimIndent())
    }


}