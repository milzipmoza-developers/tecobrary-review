package dev.milzipmoza.review.domain.review

interface ReviewAggregation {

    fun getTop(top: Long): List<CountedReview>

    fun getBriefKeywords(isbn: String): CountedReviewKeywords

    fun getBriefReviews(isbn: String): CountedReviewSelectables

    fun getRangeCount(isbn: String): CountedReviewRanges
}