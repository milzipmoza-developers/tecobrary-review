package dev.milzipmoza.review.domain.authentication

import io.kotest.core.spec.style.StringSpec
import java.time.LocalDateTime
import java.util.UUID
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class AuthenticationConfirmTest : StringSpec({

    "인증이 만료되었으면 예외가 발생한다." {
        val now = LocalDateTime.now().minusDays(31)

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                accessToken = "access-token",
                deviceId = UUID.randomUUID().toString(),
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        val confirm = AuthenticationConfirm(authentication)

        assertThrows<IllegalAccessException> {
            confirm.expired()
        }
    }

    "인증이 만료되지 않았으면 예외가 발생하지 않는다." {
        val now = LocalDateTime.now().minusDays(29)

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                accessToken = "access-token",
                deviceId = UUID.randomUUID().toString(),
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        val confirm = AuthenticationConfirm(authentication)

        assertDoesNotThrow {
            confirm.expired()
        }
    }

    "디바이스 아이디가 다르면 예외가 발생한다." {
        val now = LocalDateTime.now().minusDays(29)
        val deviceId = UUID.randomUUID().toString()

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                accessToken = "access-token",
                deviceId = deviceId,
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        val confirm = AuthenticationConfirm(authentication)

        assertThrows<DeviceNotMatchException> {
            confirm.device("a")
        }
    }

    "디바이스 아이디가 같으면 예외가 발생하지 않는다." {
        val now = LocalDateTime.now().minusDays(29)
        val deviceId = UUID.randomUUID().toString()

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                accessToken = "access-token",
                deviceId = deviceId,
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        val confirm = AuthenticationConfirm(authentication)

        assertDoesNotThrow {
            confirm.device(deviceId)
        }
    }
})