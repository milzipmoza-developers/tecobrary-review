package dev.milzipmoza.review.domain.authentication

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDateTime
import java.util.*

class AuthenticationTest : StringSpec({

    "최종 로그인 일시를 업데이트 한다." {
        val now = LocalDateTime.now()

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                accessToken = "access-token",
                deviceId = UUID.randomUUID().toString(),
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )


        val renew = authentication.renew()

        authentication.lastLoginDateTime shouldNotBe renew.lastLoginDateTime
        authentication.createdDateTime shouldBe renew.createdDateTime
    }

    "인증 시간 만료여부를 확인한다. 30일이 지나지 않으면 유효하다." {

        val now = LocalDateTime.now()

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                accessToken = "access-token",
                deviceId = UUID.randomUUID().toString(),
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        authentication.isExpired() shouldBe false
    }

    "인증 시간 만료여부를 확인한다. 30일이 지나면 만료된다." {

        val now = LocalDateTime.now().minusDays(30).minusMinutes(1)

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                accessToken = "access-token",
                deviceId = UUID.randomUUID().toString(),
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        authentication.isExpired() shouldBe true
    }

    "디바이스 ID 가 인증한 디바이스인지 확인한다." {

        val now = LocalDateTime.now()

        val deviceId = UUID.randomUUID().toString()

        val authentication = Authentication(
                code = UUID.randomUUID().toString(),
                accessToken = "access-token",
                deviceId = deviceId,
                memberNo = "member-no",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        authentication.isNotAuthenticatedDevice(deviceId) shouldBe false
    }
})