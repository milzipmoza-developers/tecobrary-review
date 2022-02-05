package dev.milzipmoza.review.api.endpoint.review


data class SelectReviewRangeResultDto(
        val draftSaved: Boolean,
        val draftReviewNo: String? = null,
)
