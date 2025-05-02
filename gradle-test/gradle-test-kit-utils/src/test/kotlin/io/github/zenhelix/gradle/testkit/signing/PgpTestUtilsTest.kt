package io.github.zenhelix.gradle.testkit.signing

import io.github.zenhelix.gradle.testkit.signing.PgpTestUtils.BOUNCY_CASTLE_PROVIDER_NAME
import io.github.zenhelix.gradle.testkit.signing.PgpTestUtils.SymmetricAlgorithm.AES_192
import io.github.zenhelix.gradle.testkit.signing.PgpTestUtils.generateArmoredPgpSecretKey
import org.assertj.core.api.Assertions.assertThat
import org.bouncycastle.openpgp.PGPSecretKeyRing
import org.bouncycastle.openpgp.PGPUtil.getDecoderStream
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class PgpTestUtilsTest {

    companion object {
        private const val TEST_PASSPHRASE = "test-passphrase"
    }

    @Test
    fun `generateArmoredPgpSecretKey should return valid PGP key string with default config`() {
        val keyString = generateArmoredPgpSecretKey(TEST_PASSPHRASE)

        assertThat(keyString).isNotEmpty()
        assertThat(keyString).contains("-----BEGIN PGP PRIVATE KEY BLOCK-----")
        assertThat(keyString).contains("-----END PGP PRIVATE KEY BLOCK-----")

        val keyRing = parsePgpKeyRing(keyString)
        assertThat(keyRing).isNotNull
        assertThat(keyRing.secretKey).isNotNull

        val privateKey = keyRing.secretKey.extractPrivateKey(
            JcePBESecretKeyDecryptorBuilder(JcaPGPDigestCalculatorProviderBuilder().setProvider(BOUNCY_CASTLE_PROVIDER_NAME).build()).setProvider(BOUNCY_CASTLE_PROVIDER_NAME).build(TEST_PASSPHRASE.toCharArray())
        )
        assertThat(privateKey).isNotNull
    }

    @Test
    fun `should generate key pair with custom configuration using DSL`() {
        val keyString = generateArmoredPgpSecretKey(TEST_PASSPHRASE) {
            identity = "custom@example.org"
            keySize = 4096
        }

        assertThat(keyString).isNotEmpty()
        val keyRing = parsePgpKeyRing(keyString)
        assertThat(keyRing.secretKey.publicKey.bitStrength).isEqualTo(4096)

        val userIDs = keyRing.secretKey.publicKey.userIDs.asSequence().toList()
        assertThat(userIDs).anyMatch { it.contains("custom@example.org") }
    }

    @Test
    fun `generateArmoredKeyPair should return valid public and private keys`() {
        val (privateKey, publicKey) = PgpTestUtils.generateArmoredKeyPair(TEST_PASSPHRASE)

        assertThat(privateKey).contains("-----BEGIN PGP PRIVATE KEY BLOCK-----")
        assertThat(publicKey).contains("-----BEGIN PGP PUBLIC KEY BLOCK-----")

        val secretKeyRing = parsePgpKeyRing(privateKey)
        assertThat(secretKeyRing).isNotNull

        val publicKeyRing = getDecoderStream(ByteArrayInputStream(publicKey.toByteArray())).use { decoderStream ->
            org.bouncycastle.openpgp.PGPPublicKeyRing(decoderStream, JcaKeyFingerprintCalculator())
        }
        assertThat(publicKeyRing).isNotNull

        assertThat(publicKeyRing.publicKey.keyID).isEqualTo(secretKeyRing.secretKey.keyID)
    }

    @Test
    fun `should customize key with all configuration options using DSL`() {
        val keyString = generateArmoredPgpSecretKey(TEST_PASSPHRASE) {
            identity = "test-custom@example.org"
            keySize = 3072
            symmetricAlgorithm = AES_192
        }

        assertThat(keyString).isNotEmpty()
        val keyRing = parsePgpKeyRing(keyString)
        assertThat(keyRing.secretKey.publicKey.bitStrength).isEqualTo(3072)

        val userIDs = keyRing.secretKey.publicKey.userIDs.asSequence().toList()
        assertThat(userIDs).anyMatch { it.contains("test-custom@example.org") }
    }

    @Test
    fun `generateArmoredKeyPair should create keys with custom config using DSL`() {
        val (privateKey, publicKey) = PgpTestUtils.generateArmoredKeyPair(TEST_PASSPHRASE) {
            identity = "keypair-test@example.org"
            keySize = 3072
        }

        assertThat(privateKey).contains("-----BEGIN PGP PRIVATE KEY BLOCK-----")
        assertThat(publicKey).contains("-----BEGIN PGP PUBLIC KEY BLOCK-----")

        val secretKeyRing = parsePgpKeyRing(privateKey)
        assertThat(secretKeyRing.secretKey.publicKey.bitStrength).isEqualTo(3072)

        val userIDs = secretKeyRing.secretKey.publicKey.userIDs.asSequence().toList()
        assertThat(userIDs).anyMatch { it.contains("keypair-test@example.org") }
    }

    private fun parsePgpKeyRing(keyData: String): PGPSecretKeyRing =
        getDecoderStream(ByteArrayInputStream(keyData.toByteArray())).use { decoderStream ->
            PGPSecretKeyRing(decoderStream, JcaKeyFingerprintCalculator())
        }
}