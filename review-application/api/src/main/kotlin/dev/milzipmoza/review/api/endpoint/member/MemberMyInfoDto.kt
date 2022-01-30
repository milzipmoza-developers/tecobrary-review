package dev.milzipmoza.review.api.endpoint.member

data class MemberMyInfoDto(
        val member: MemberDto
)

data class MemberDto(
        val name: String,
        val email: String,
        val profileImageUrl: String,
        val blogUrl: String,
        val description: String
)