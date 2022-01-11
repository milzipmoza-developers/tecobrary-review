package dev.milzipmoza.review.domain.authentication

import dev.milzipmoza.review.domain.member.model.Member
import java.time.LocalDateTime
import java.util.*

class LoginAuthentication(
        private val member: Member,
        private val authentication: Authentication?
) {

    fun login(deviceId: String): Authentication {
        val now = LocalDateTime.now()

        if (authentication == null) {
            return Authentication(
                    code = UUID.randomUUID().toString(),
                    deviceId = deviceId,
                    memberNo = member.no,
                    createdDateTime = now,
                    lastLoginDateTime = now
            )
        }

        return authentication.renew(now)
    }
}