package dev.milzipmoza.review.domain.member.model

import dev.milzipmoza.review.domain.Entity

class Member(
        val no: String,
        val account: MemberAccount,
        val info: MemberInfo
) : Entity<Member> {

    override fun getId() = no
}