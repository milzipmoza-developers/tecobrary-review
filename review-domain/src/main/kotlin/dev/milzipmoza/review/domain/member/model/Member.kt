package dev.milzipmoza.review.domain.member.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.member.MemberOperationException

class Member(
        val no: String = "",
        val account: MemberAccount,
        val info: MemberInfo
) : Entity<Member> {

    override fun getId() = when (no.isNotBlank()) {
        true -> no
        false -> throw MemberOperationException("비교가 불가능합니다.")
    }
}