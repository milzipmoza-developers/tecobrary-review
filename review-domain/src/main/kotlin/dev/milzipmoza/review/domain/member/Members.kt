package dev.milzipmoza.review.domain.member

import dev.milzipmoza.review.domain.member.model.Member

interface Members {

    fun findBy(no: String): Member

    fun findAllIn(nos: List<String>): List<Member>

    fun isExist(memberNo: String): Boolean
}