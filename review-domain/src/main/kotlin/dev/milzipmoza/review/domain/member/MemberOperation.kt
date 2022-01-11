package dev.milzipmoza.review.domain.member

import dev.milzipmoza.review.domain.member.model.Member

interface MemberOperation {

    fun upsert(member: Member): Member
}