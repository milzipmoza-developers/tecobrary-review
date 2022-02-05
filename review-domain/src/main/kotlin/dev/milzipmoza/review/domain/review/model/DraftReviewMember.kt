package dev.milzipmoza.review.domain.review.model

class DraftReviewMember(
        val no: String? = null,
        val deviceId: String,
) {

    fun isNotSameMember(other: DraftReviewMember) = !isSameMember(other)

    private fun isSameMember(other: DraftReviewMember) : Boolean {
        if (no == null) {
            return deviceId == other.deviceId
        }
        return no == other.no
    }
}
