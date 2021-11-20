package dev.milzipmoza.domain.member.model

import dev.milzipmoza.domain.Entity

class Member(
        val no: String,
        val account: MemberAccount,
        val info: MemberInfo
) : Entity<Member> {

    override fun getId() = no
}