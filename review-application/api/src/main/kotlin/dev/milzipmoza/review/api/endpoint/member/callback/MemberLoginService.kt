package dev.milzipmoza.review.api.endpoint.member.callback

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
        private val authenticationOperation: AuthenticationOperation
) {

    fun processLogin(member: Member, deviceId: String): String {
        val upsertedMember = memberOperation.upsert(member)

        val authentication = authentications.findByMemberNo(upsertedMember.no)

        val loginAuthentication = LoginAuthentication(member, authentication)

        val authenticationAfterLogin = loginAuthentication.login(deviceId)

        authenticationOperation.save(authenticationAfterLogin)

        return authenticationAfterLogin.code
    }
}