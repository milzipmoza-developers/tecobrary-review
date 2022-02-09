package dev.milzipmoza.review.api

data class AdminMemberDto(
        val memberNo: String,
        val deviceId: String
)  {
    companion object {
        const val ATTRIBUTE_NAME = "adminMember"
    }
}