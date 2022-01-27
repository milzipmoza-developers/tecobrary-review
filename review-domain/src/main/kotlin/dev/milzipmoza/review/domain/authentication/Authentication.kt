package dev.milzipmoza.review.domain.authentication

import dev.milzipmoza.review.domain.Entity
import java.time.LocalDateTime

class Authentication(
        val code: String,
        val accessToken: String,
        val deviceId: String,
        val memberNo: String,
        val createdDateTime: LocalDateTime,
        lastLoginDateTime: LocalDateTime,
) : Entity<Authentication> {
    var lastLoginDateTime = lastLoginDateTime
        private set

    val expiredDateTime = createdDateTime.plusDays(30)!!

    fun renew(): Authentication {
        return Authentication(
                code = this.code,
                accessToken = this.accessToken,
                deviceId = this.deviceId,
                memberNo = this.memberNo,
                createdDateTime = this.createdDateTime,
                lastLoginDateTime = LocalDateTime.now()
        )
    }

    internal fun isExpired() = expiredDateTime.isBefore(LocalDateTime.now())

    internal fun isNotAuthenticatedDevice(checkDeviceId: String) = this.deviceId != checkDeviceId

    override fun getId() = code
}