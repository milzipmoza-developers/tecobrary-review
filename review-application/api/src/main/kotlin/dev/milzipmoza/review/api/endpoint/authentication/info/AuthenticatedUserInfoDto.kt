package dev.milzipmoza.review.api.endpoint.authentication.info

data class AuthenticatedUserInfoDto(
        val memberNo: String,
        val memberName: String,
        val profileImageUrl: String,
)