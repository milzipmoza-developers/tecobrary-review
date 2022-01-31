package dev.milzipmoza.review.api

data class AuthMemberDto(
        val memberNo: String,
        val deviceId: String
)  {
    companion object {
        const val ATTRIBUTE_NAME = "tecobraryMember"
    }
}