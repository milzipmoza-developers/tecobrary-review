package dev.milzipmoza.review.api.endpoint.review.selectbook

data class SelectReviewBookResultDto(
        val draftSaved: Boolean,
        val draftReviewNo: String? = null
)
