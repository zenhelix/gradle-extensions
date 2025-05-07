package io.github.zenhelix.gradle.test.dsl.task

import io.github.zenhelix.gradle.test.dsl.GradleDslImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SigningDslTest {

    @Test
    fun `should correctly format in-memory pgp keys with multiline string`() {
        assertThat(
            SigningDsl(GradleDslImpl()).apply {
                useInMemoryPgpKeys(
                    """-----BEGIN PGP PRIVATE KEY BLOCK-----
                      |Version: BCPG v1.58
                      |
                      |lQPGBGRw6UYBCADSPQfn+rQKQYJH5WwFjeSrLzx+hOYLkVfZjrwKqnncszzcSEkA
                      |-----END PGP PRIVATE KEY BLOCK-----""".trimMargin(),
                    "password123"
                )
            }.build()
        ).isEqualTo(
            """useInMemoryPgpKeys(""${'"'}-----BEGIN PGP PRIVATE KEY BLOCK-----
              |Version: BCPG v1.58
              |
              |lQPGBGRw6UYBCADSPQfn+rQKQYJH5WwFjeSrLzx+hOYLkVfZjrwKqnncszzcSEkA
              |-----END PGP PRIVATE KEY BLOCK-----""${'"'}, "password123")
              |""".trimMargin()
        )
    }

    @Test
    fun `should handle pgp keys with triple quotes`() {
        assertThat(
            SigningDsl(GradleDslImpl()).apply {
                useInMemoryPgpKeys(
                    """-----BEGIN PGP PRIVATE KEY BLOCK-----
                      |Some text with ""${'"'} triple quotes
                      |-----END PGP PRIVATE KEY BLOCK-----""".trimMargin(),
                    "password123"
                )
            }.build()
        ).isEqualTo(
            """useInMemoryPgpKeys(""${'"'}-----BEGIN PGP PRIVATE KEY BLOCK-----
              |Some text with ""${'"'} + ""${'"'} triple quotes
              |-----END PGP PRIVATE KEY BLOCK-----""${'"'}, "password123")
              |""".trimMargin()
        )
    }

    @Test
    fun `should handle strings with escape sequences correctly`() {
        assertThat(
            SigningDsl(GradleDslImpl()).apply {
                useInMemoryPgpKeys(
                    """-----BEGIN PGP PRIVATE KEY BLOCK-----
                      |Text with \t tabs and \n explicit newlines
                      |Even \r carriage returns and \\ backslashes
                      |-----END PGP PRIVATE KEY BLOCK-----""".trimMargin(),
                    "password\t123"
                )
            }.build()
        ).isEqualTo(
            """useInMemoryPgpKeys(""${'"'}-----BEGIN PGP PRIVATE KEY BLOCK-----
              |Text with \t tabs and \n explicit newlines
              |Even \r carriage returns and \\ backslashes
              |-----END PGP PRIVATE KEY BLOCK-----""${'"'}, "password	123")
              |""".trimMargin()
        )
    }

    @Test
    fun `should handle strings with dollar signs and curly braces correctly`() {
        assertThat(
            SigningDsl(GradleDslImpl()).apply {
                useInMemoryPgpKeys(
                    """-----BEGIN PGP PRIVATE KEY BLOCK-----
                      |Text with ${1 + 1} interpolation syntax
                      |And ${"""nested strings"""} too
                      |-----END PGP PRIVATE KEY BLOCK-----""".trimMargin(),
                    "password${123}"
                )
            }.build()
        ).isEqualTo(
            """useInMemoryPgpKeys(""${'"'}-----BEGIN PGP PRIVATE KEY BLOCK-----
              |Text with 2 interpolation syntax
              |And nested strings too
              |-----END PGP PRIVATE KEY BLOCK-----""${'"'}, "password${123}")
              |""".trimMargin()
        )
    }

    @Test
    fun `should handle combined special characters correctly`() {
        assertThat(
            SigningDsl(GradleDslImpl()).apply {
                useInMemoryPgpKeys(
                    """-----BEGIN PGP PRIVATE KEY BLOCK-----
                      |Complex string with "double quotes", 'single quotes',
                      |newlines, \t tabs, ${"""interpolation"""}, and even
                      |some ""${'"'} triple quotes for good measure
                      |-----END PGP PRIVATE KEY BLOCK-----""".trimMargin(),
                    "complex!@#$%^&*()_+{}:<>?|~`-=[]\\;',./ password"
                )
            }.build()
        ).isEqualTo(
            """useInMemoryPgpKeys(""${'"'}-----BEGIN PGP PRIVATE KEY BLOCK-----
              |Complex string with "double quotes", 'single quotes',
              |newlines, \t tabs, interpolation, and even
              |some ""${'"'} + ""${'"'} triple quotes for good measure
              |-----END PGP PRIVATE KEY BLOCK-----""${'"'}, "complex!@#$%^&*()_+{}:<>?|~`-=[]\;',./ password")
              |""".trimMargin()
        )
    }

    @Test
    fun `should format non-multiline string normally when no special characters present`() {
        assertThat(
            SigningDsl(GradleDslImpl()).apply {
                useInMemoryPgpKeys(
                    "simple key without special characters",
                    "simple password"
                )
            }.build()
        ).isEqualTo(
            """useInMemoryPgpKeys("simple key without special characters", "simple password")
              |""".trimMargin()
        )
    }

    @Test
    fun `should handle empty strings correctly`() {
        assertThat(
            SigningDsl(GradleDslImpl()).apply {
                useInMemoryPgpKeys("", "")
            }.build()
        ).isEqualTo(
            """useInMemoryPgpKeys("", "")
              |""".trimMargin()
        )
    }

}