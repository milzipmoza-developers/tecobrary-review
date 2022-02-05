package dev.milzipmoza.review.api.endpoint.review

data class SelectReviewBookResultDto(
        val draftSaved: Boolean,
        val draftReviewNo: String? = null
)
