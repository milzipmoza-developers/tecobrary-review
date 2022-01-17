package dev.milzipmoza.review.domain.authentication

import dev.milzipmoza.review.domain.Entity
import java.time.LocalDateTime

class Authentication(
        val code: String,
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
                deviceId = this.deviceId,
                memberNo = this.memberNo,
                createdDateTime = this.createdDateTime,
                lastLoginDateTime = LocalDateTime.now()
        )
    }

    fun isExpired() = expiredDateTime.isAfter(LocalDateTime.now())

    fun isNotAuthenticatedDevice(checkDeviceId: String) = this.deviceId != checkDeviceId

    override fun getId() = code

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Authentication

        if (code != other.code) return false
        if (deviceId != other.deviceId) return false
        if (memberNo != other.memberNo) return false
        if (createdDateTime != other.createdDateTime) return false
        if (lastLoginDateTime != other.lastLoginDateTime) return false
        if (expiredDateTime != other.expiredDateTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + deviceId.hashCode()
        result = 31 * result + memberNo.hashCode()
        result = 31 * result + createdDateTime.hashCode()
        result = 31 * result + lastLoginDateTime.hashCode()
        result = 31 * result + expiredDateTime.hashCode()
        return result
    }
}