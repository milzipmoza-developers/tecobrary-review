package dev.milzipmoza.review.domain.review

interface ReviewAggregation {

    fun getTop(top: Long): List<CountedReview>
}