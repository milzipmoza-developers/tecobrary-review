package dev.milzipmoza.review.mongo.review

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoDraftReviewRepository : MongoRepository<DocumentDraftReview, ObjectId> {

    fun findByNo(no: String): DocumentDraftReview?
}