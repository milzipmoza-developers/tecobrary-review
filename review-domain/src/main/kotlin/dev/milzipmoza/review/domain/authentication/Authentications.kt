package dev.milzipmoza.review.domain.authentication

interface Authentications {

    fun findByCode(code: String): Authentication?

    fun findByMemberNo(memberNo: String): Authentication?
}