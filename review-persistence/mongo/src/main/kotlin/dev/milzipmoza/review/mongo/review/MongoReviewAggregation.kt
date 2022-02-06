package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.CountedReview
import dev.milzipmoza.review.domain.review.ReviewAggregation
import org.springframework.stereotype.Repository

@Repository
class MongoReviewAggregation(
        private val mongoReviewAggregator: MongoReviewAggregator
) : ReviewAggregation {

    override fun getTop(top: Long): List<CountedReview> {
        val documents = mongoReviewAggregator.findTop(top)
        return documents.map { CountedReview(it.bookIsbn ,it.count) }
    }
}