package dev.milzipmoza.domain.member.model

class MemberAccount(
        val identifier: String,
        val provider: MemberOAuthProvider,
        val email: String
) {

}
