package dev.milzipmoza.review.domain.authentication

import dev.milzipmoza.review.domain.member.model.Member
import java.time.LocalDateTime
import java.util.UUID

class LoginAuthentication(
        private val member: Member,
        private val authentication: Authentication?
) {

    fun login(deviceId: String, accessToken: String): Authentication {
        val now = LocalDateTime.now()

        if (authentication == null) {
            return Authentication(
                    code = UUID.randomUUID().toString(),
                    accessToken = accessToken,
                    deviceId = deviceId,
                    memberNo = member.no,
                    createdDateTime = now,
                    lastLoginDateTime = now
            )
        }

        if (authentication.isNotAuthenticatedDevice(deviceId)) {
            throw IllegalAccessException("다시 로그인을 시도해주세요.")
        }

        return authentication.renew()
    }
}