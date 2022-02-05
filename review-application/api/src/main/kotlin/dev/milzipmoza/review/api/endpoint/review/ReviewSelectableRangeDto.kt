package dev.milzipmoza.review.api.endpoint.review


data class ReviewSelectableRangeDto(
        val ranges: List<ReviewRangeDto>
)

data class ReviewRangeDto(
        val displayOrder: Int,
        val displayName: String,
        val key: String,
        val disabled: Boolean
)
