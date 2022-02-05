package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.ReviewOperation
import dev.milzipmoza.review.domain.review.model.Review
import org.springframework.stereotype.Repository

@Repository
class MongoReviewOperation(
        private val mongoReviewRepository: MongoReviewRepository
) : ReviewOperation {

    override fun save(review: Review): Boolean {
        val documentReview = when (review) {
            is Review.SimpleReview -> {
                DocumentReview(
                        member = DocumentReviewMember(no = review.member.no),
                        book = DocumentReviewBook(
                                isbn = review.book.isbn,
                                title = review.book.title
                        ),
                        range = review.range.name,
                        keyword = DocumentReviewKeyword(
                                content = review.keyword.content.name,
                                informative = review.keyword.informative.name,
                                readMore = review.keyword.readMore?.name,
                                selectables = review.keyword.selectables.map { it.name }.toSet()
                        ),
                )
            }
            else -> throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }

        return try {
            mongoReviewRepository.save(documentReview)
            true
        } catch (e: Exception) {
            false
        }
    }
}