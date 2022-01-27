package dev.milzipmoza.review.jwt

import dev.milzipmoza.review.domain.authentication.Authentication
import dev.milzipmoza.review.mongo.extension.Logger
import java.time.LocalDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class JwtAuthenticatorTest {

    @Autowired
    private lateinit var jwtAuthenticator: JwtAuthenticator

    private val log = Logger.of(this)

    @Test
    fun name() {
        val authentication = Authentication(
                code = "a",
                accessToken = "b",
                deviceId = "c",
                memberNo = "d",
                createdDateTime = LocalDateTime.now(),
                lastLoginDateTime = LocalDateTime.now()
        )

        assertDoesNotThrow {
            val jwt = jwtAuthenticator.createToken(authentication)
            log.info(jwt)
        }
    }
}