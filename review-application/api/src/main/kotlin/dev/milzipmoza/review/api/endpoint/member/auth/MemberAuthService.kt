package dev.milzipmoza.review.api.endpoint.member.auth

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.authentication.AuthenticationConfirm
import dev.milzipmoza.review.domain.authentication.Authentications
import dev.milzipmoza.review.jwt.JwtAuthenticator
import dev.milzipmoza.review.mongo.extension.Logger

@ApplicationService
class MemberAuthService(
        private val authentications: Authentications,
        private val jwtAuthenticator: JwtAuthenticator
) {
    private val log = Logger.of(this)

    fun createToken(deviceId: String, code: String): String {
        log.debug("[MemberAuthService] deviceId=$deviceId, code=$code")

        val authentication = authentications.findBy(code)
                ?: throw IllegalAccessException("다시 로그인 해주세요.")

        val confirm = AuthenticationConfirm(authentication)

        confirm.expired()
        confirm.device(deviceId)

        return jwtAuthenticator.createToken(authentication)
    }
}