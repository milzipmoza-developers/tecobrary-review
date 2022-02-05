package dev.milzipmoza.review.domain.member

import dev.milzipmoza.review.domain.member.model.Member

interface Members {

    fun findBy(no: String): Member

    fun isExist(memberNo: String): Boolean
}