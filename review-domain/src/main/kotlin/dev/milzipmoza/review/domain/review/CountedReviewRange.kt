package dev.milzipmoza.review.domain.review

import dev.milzipmoza.review.domain.review.model.ReviewReadRange

class CountedReviewRanges(
        val ranges: List<CountedReviewRange>
) {
    fun getCount(range: ReviewReadRange): Long {
        return ranges.find { it.range == range }?.count ?: 0
    }
}

class CountedReviewRange(
        range: String,
        val count: Long
) {
    val range: ReviewReadRange = ReviewReadRange.valueOf(range)
}
