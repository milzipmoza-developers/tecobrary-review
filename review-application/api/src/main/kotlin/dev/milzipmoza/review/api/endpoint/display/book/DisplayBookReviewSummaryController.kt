package dev.milzipmoza.review.api.endpoint.display.book

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.domain.review.ReviewAggregation
import dev.milzipmoza.review.domain.review.Reviews
import dev.milzipmoza.review.domain.review.model.ReviewReadRange
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DisplayBookReviewSummaryController(
        private val reviews: Reviews,
        private val reviewAggregation: ReviewAggregation
) {

    @GetMapping("/api/displays/books/{isbn}/review-ranges")
    fun summarize(@PathVariable isbn: String): ApiResponse<DisplayBookReviewSummaryDto> {
        val total = reviews.count(isbn)
        val countedRanges = reviewAggregation.getRangeCount(isbn)
        val ranges = ReviewReadRange.values()
                .map { DisplayBookReviewRangeSummaryDto(it.name, it.displayName, countedRanges.getCount(it)) }

        return ApiResponse.success(
                data = DisplayBookReviewSummaryDto(
                        total = total,
                        ranges = ranges
                )
        )
    }
}
