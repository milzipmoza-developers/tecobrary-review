package dev.milzipmoza.review.mongo.review

import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoReviewRepository : MongoRepository<DocumentReview, ObjectId>, CustomMongoReviewRepository {

    fun countAllByBookIsbn(bookIsbn: String): Long
}

interface CustomMongoReviewRepository {

    fun findAllByMemberNoAndBookNo(memberNo: String, bookIsbn: String): List<DocumentReview>

    fun findRecentAfter(size: Int, id: ObjectId?): List<DocumentReview>
}

@Repository
class CustomMongoReviewRepositoryImpl(
        private val mongoTemplate: MongoTemplate
) : CustomMongoReviewRepository {

    override fun findAllByMemberNoAndBookNo(memberNo: String, bookIsbn: String): List<DocumentReview> {
        val criteria = Criteria.where("member.no").`is`(memberNo)
                .and("book.isbn").`is`(bookIsbn)

        val query = Query.query(criteria)

        return mongoTemplate.find(query, DocumentReview::class.java)
    }

    override fun findRecentAfter(size: Int, id: ObjectId?): List<DocumentReview> {
        if (id == null) {
            val query = Query()
                    .with(Sort.by(Sort.Direction.DESC, "id"))
                    .limit(size)

            return mongoTemplate.find(query, DocumentReview::class.java)
        }

        val criteria = Criteria.where("id").lt(id)

        val query = Query.query(criteria)
                .with(Sort.by(Sort.Direction.DESC, "id"))
                .limit(size)

        return mongoTemplate.find(query, DocumentReview::class.java)
    }
}