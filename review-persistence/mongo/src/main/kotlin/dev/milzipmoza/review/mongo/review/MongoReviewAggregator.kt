package dev.milzipmoza.review.mongo.review

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.stereotype.Repository

interface MongoReviewAggregator {

    fun findTop(count: Long): List<DocumentReviewFindTopResult>
}

@Repository
class MongoReviewAggregatorImpl(
        private val mongoTemplate: MongoTemplate
) : MongoReviewAggregator {

    override fun findTop(count: Long): List<DocumentReviewFindTopResult> {
        val group = Aggregation.group("book.isbn").count().`as`("count")
        val sort = Aggregation.sort(Sort.by(Sort.Direction.DESC, "count"))
        val limit = Aggregation.limit(count)

        val aggregation = Aggregation.newAggregation(group, sort, limit)
        val result = mongoTemplate.aggregate(aggregation, DocumentReview::class.java, DocumentReviewFindTopResult::class.java)
        return result.mappedResults
    }
}

data class DocumentReviewFindTopResult(
        @Field("_id")
        val bookIsbn: String,
        val count: Int
)