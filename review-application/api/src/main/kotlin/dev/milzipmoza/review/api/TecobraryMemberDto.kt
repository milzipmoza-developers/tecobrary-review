package dev.milzipmoza.review.api

data class TecobraryMemberDto(
        val memberNo: String,
        val deviceId: String
)  {
    companion object {
        const val ATTRIBUTE_NAME = "tecobraryMember"
    }
}