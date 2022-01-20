package dev.milzipmoza.review.api.endpoint.member.callback

import dev.milzipmoza.review.mongo.extension.Logger
import org.junit.jupiter.api.Test

class Aes256EncryptorTest {

    private val log = Logger.of(this)

    private val aes256Encryptor = Aes256Encryptor("01234567890123456789012345678901")

    @Test
    internal fun name() {
        val encrypt = aes256Encryptor.encrypt("hello world")

        log.info("encrypt={}", encrypt)

        val decrypt = aes256Encryptor.decrypt(encrypt)

        log.info("decrypt={}", decrypt)
    }
}