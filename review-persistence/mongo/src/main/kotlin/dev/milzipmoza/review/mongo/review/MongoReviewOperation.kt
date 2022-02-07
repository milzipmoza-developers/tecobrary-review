package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.ReviewOperation
import dev.milzipmoza.review.domain.review.model.Review
import org.springframework.stereotype.Repository

@Repository
class MongoReviewOperation(
        private val mongoReviewRepository: MongoReviewRepository
) : ReviewOperation {

    override fun save(review: Review): Boolean {
        val documentReview = DocumentReviewMapper.map(review)

        return try {
            mongoReviewRepository.save(documentReview)
            true
        } catch (e: Exception) {
            false
        }
    }
}