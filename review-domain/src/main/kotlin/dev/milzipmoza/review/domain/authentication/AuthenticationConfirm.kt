package dev.milzipmoza.review.domain.authentication

class AuthenticationConfirm(
        private val authentication: Authentication
) {

    fun expired() {
        if (authentication.isExpired()) {
            throw IllegalAccessException("로그인한지 너무 오래되었어요. 다시 로그인해주세요.")
        }
    }

    fun device(deviceId: String) {
        if (authentication.isNotAuthenticatedDevice(deviceId)) {
            throw DeviceNotMatchException("기기 정보가 변경되었어요. 다시 로그인해주세요.")
        }
    }
}