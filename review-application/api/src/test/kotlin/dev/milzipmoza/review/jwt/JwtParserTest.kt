package dev.milzipmoza.review.jwt

import dev.milzipmoza.review.mongo.extension.Logger
import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class JwtParserTest {

    private val log = Logger.of(this)

    @Test
    fun encode() {
        val jwtParser = JwtParser("aaaa", "bbbb", "cccc", "dddd", "eeee")

        val now = LocalDateTime.now()

        assertDoesNotThrow {
            val token = jwtParser.encode("a", now, now.plusDays(30))
            log.info(token)
        }
    }
}