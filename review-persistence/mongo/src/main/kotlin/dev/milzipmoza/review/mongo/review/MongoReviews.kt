package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.Reviews
import dev.milzipmoza.review.domain.review.model.EnrolledReviews
import dev.milzipmoza.review.domain.review.model.Review
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoReviews(
        private val mongoReviewRepository: MongoReviewRepository
) : Reviews {

    override fun getAll(memberNo: String, bookIsbn: String): EnrolledReviews {
        val documents = mongoReviewRepository.findAllByMemberNoAndBookNo(memberNo, bookIsbn)

        val reviews = documents.map { DocumentReviewMapper.map(it) }

        return EnrolledReviews(reviews)
    }

    override fun count(bookIsbn: String): Long {
        return mongoReviewRepository.countAllByBookIsbn(bookIsbn)
    }

    override fun getRecent(size: Int, lastReviewNo: String?): List<Review> {
        return mongoReviewRepository.findRecentAfter(size, lastReviewNo?.let { ObjectId(it) })
                .map { DocumentReviewMapper.map(it) }
    }

    override fun getLatest(): Review? {
        val latestDocument = mongoReviewRepository.findLatest()
        return latestDocument?.let { DocumentReviewMapper.map(it) }

    }
}