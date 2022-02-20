package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.Reviews
import dev.milzipmoza.review.domain.review.model.EnrolledReviews
import dev.milzipmoza.review.domain.review.model.Review
import dev.milzipmoza.review.domain.review.model.ReviewBook
import dev.milzipmoza.review.domain.review.model.ReviewKeyword
import dev.milzipmoza.review.domain.review.model.ReviewMember
import dev.milzipmoza.review.domain.review.model.ReviewReadRange
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoReviews(
        private val mongoReviewRepository: MongoReviewRepository
) : Reviews {

    override fun getAll(memberNo: String, isbn: String): EnrolledReviews {
        val documents = mongoReviewRepository.findAllByMemberNoAndBookNo(memberNo, isbn)

        val reviews = documents.map { DocumentReviewMapper.map(it) }

        return EnrolledReviews(reviews)
    }

    override fun count(bookIsbn: String): Long {
        return mongoReviewRepository.countAllByBookIsbn(bookIsbn)
    }

    override fun getRecent(size: Int, noAfter: String?): List<Review> {
        return mongoReviewRepository.findRecentAfter(size, ObjectId(noAfter))
                .map { DocumentReviewMapper.map(it) }
    }
}