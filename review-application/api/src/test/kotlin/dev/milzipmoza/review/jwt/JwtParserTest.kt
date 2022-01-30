package dev.milzipmoza.review.jwt

import dev.milzipmoza.review.mongo.extension.Logger
import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class JwtParserTest {

    private val log = Logger.of(this)

    private val jwtParser = JwtParser("aaaa", "bbbb", "cccc", "dddd", "eeee")

    @Test
    fun encode() {
        val now = LocalDateTime.now()

        assertDoesNotThrow {
            val token = jwtParser.encode("a", now, now.plusDays(30))
            log.info(token)
        }
    }

    @Test
    fun decodeExpired() {
        val yesterday = LocalDateTime.now().minusDays(1)
        val accessToken = "a"

        val jwt = jwtParser.encode(accessToken, yesterday, yesterday)

        assertThrows<Exception> {
            jwtParser.decode(jwt)
        }
    }

    @Test
    fun decodeInvalidSecretKey() {
        val yesterday = LocalDateTime.now().minusDays(1)
        val accessToken = "a"

        val anotherJwtParser = JwtParser("22222", "bbbb", "cccc", "dddd", "eeee")

        val jwt = anotherJwtParser.encode(accessToken, yesterday, yesterday)

        assertThrows<Exception> {
            jwtParser.decode(jwt)
        }
    }

    @Test
    fun decode() {
        val now = LocalDateTime.now()
        val token = jwtParser.encode("a", now, now.plusDays(30))

        assertDoesNotThrow {
            jwtParser.decode(token)
        }
    }
}