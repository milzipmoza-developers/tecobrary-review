package dev.milzipmoza.review.domain.authentication

import java.time.LocalDateTime
import java.util.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class AuthenticationTest {

    @Test
    internal fun renew() {
        val now = LocalDateTime.now()

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                deviceId = UUID.randomUUID().toString(),
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )


        val renew = authentication.renew(LocalDateTime.now())

        assertNotEquals(authentication.lastLoginDateTime, renew.lastLoginDateTime)
        assertEquals(authentication.createdDateTime, renew.createdDateTime)
    }
}