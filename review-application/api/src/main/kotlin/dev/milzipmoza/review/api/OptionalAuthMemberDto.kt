package dev.milzipmoza.review.api

data class OptionalAuthMemberDto(
        val memberNo: String,
        val deviceId: String
)  {
    companion object {
        const val ATTRIBUTE_NAME = "optionalAuthMember"
    }
}