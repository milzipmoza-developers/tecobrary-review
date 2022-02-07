package dev.milzipmoza.review.api.endpoint.display.book


data class DisplayBookReviewSummaryDto(
        val total: Long,
        val ranges: List<DisplayBookReviewRangeSummaryDto>
)

data class DisplayBookReviewRangeSummaryDto(
        val range: String,
        val displayName: String,
        val count: Long
)
