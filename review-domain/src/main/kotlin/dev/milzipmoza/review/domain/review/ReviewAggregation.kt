package dev.milzipmoza.review.domain.review

import dev.milzipmoza.review.domain.review.model.ReviewReadRange

interface ReviewAggregation {

    fun getTop(top: Long): List<CountedReview>

    fun getBriefKeywords(isbn: String, range: ReviewReadRange): CountedReviewKeywords

    fun getBriefReviews(isbn: String, range: ReviewReadRange): CountedReviewSelectables

    fun getRangeCount(isbn: String): CountedReviewRanges
}