package dev.milzipmoza.review.jwt

import java.time.LocalDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DecodedJwtTest {

    @Test
    fun construct() {
        val now = LocalDateTime.now()
        val issuer = "bbbb"
        val subject = "cccc"
        val version = "eeee"
        val accessToken = "a"
        val issuedDateTime = now

        val jwtParser = JwtParser("aaaa", issuer, subject, "dddd", version)

        val token = jwtParser.encode(accessToken, issuedDateTime, now.plusDays(30))

        val decode = jwtParser.decode(token)

        val decodedJwt = DecodedJwt(decode)

        assertThat(decodedJwt.issuer).isEqualTo(issuer)
        assertThat(decodedJwt.subject).isEqualTo(subject)
        assertThat(decodedJwt.accessToken).isEqualTo(accessToken)
        assertThat(decodedJwt.version).isEqualTo(version)
        assertThat(decodedJwt.issuedDateTime).isEqualTo(issuedDateTime)
    }
}