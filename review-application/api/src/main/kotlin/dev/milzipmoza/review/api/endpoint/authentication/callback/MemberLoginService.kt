package dev.milzipmoza.review.api.endpoint.authentication.callback

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.authentication.AuthenticationOperation
import dev.milzipmoza.review.domain.authentication.Authentications
import dev.milzipmoza.review.domain.authentication.LoginAuthentication
import dev.milzipmoza.review.domain.member.MemberOperation
import dev.milzipmoza.review.domain.member.model.Member

@ApplicationService
class MemberLoginService(
        private val memberOperation: MemberOperation,

        private val authentications: Authentications,
        private val authenticationOperation: AuthenticationOperation,

        private val memberAccessTokenFactory: MemberAccessTokenFactory
) {

    fun processLogin(member: Member, deviceId: String): String {
        val upsertedMember = memberOperation.upsert(member)

        val authentication = authentications.findBy(upsertedMember.no, deviceId)

        val loginAuthentication = LoginAuthentication(upsertedMember, authentication)

        val accessToken = memberAccessTokenFactory.create(upsertedMember.no)

        val authenticationAfterLogin = loginAuthentication.login(deviceId, accessToken)

        authenticationOperation.save(authenticationAfterLogin)

        return authenticationAfterLogin.code
    }
}