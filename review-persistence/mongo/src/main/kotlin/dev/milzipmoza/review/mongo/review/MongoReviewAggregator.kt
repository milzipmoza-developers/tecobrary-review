package dev.milzipmoza.review.mongo.review

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface MongoReviewAggregator : MongoRepository<DocumentReview, ObjectId>, CustomMongoReviewAggregator {

    @Aggregation(pipeline = [
        "{ \$match: {'book.isbn': '?0', 'range': '?1' } }",
        "{ \$project: { a: { content: '\$keyword.content', informative: '\$keyword.informative', readMore: '\$keyword.readMore' } } }",
        "{ \$project: { a: {\$objectToArray: '\$a'} } }",
        "{ \$unwind: '\$a' }",
        "{ \$group: {_id: {key: '\$a.k', value: '\$a.v'}, count: {\$sum: 1} } }"
    ])
    fun getBriefKeywords(bookIsbn: String, range: String): AggregationResults<DocumentReviewKeywordBrief>

    @Aggregation(pipeline = [
        "{ \$match: {'book.isbn': '?0', 'range': '?1' } }",
        "{ \$project: {range: '\$range', selectables: '\$keyword.selectables'} }",
        "{ \$unwind: '\$selectables' }",
        "{ \$group: {_id: {selectable: '\$selectables'}, count: {\$sum: 1} } }"
    ])
    fun getBriefReviews(bookIsbn: String, range: String): AggregationResults<DocumentReviewBrief>

    @Aggregation(pipeline = [
        "{ \$match: {'book.isbn': '?0'} }",
        "{ \$group: {_id: {range: '\$range'}, count: {\$sum: 1} } }"
    ])
    fun getCountRange(bookIsbn: String): AggregationResults<DocumentReviewRangeCount>
}

data class DocumentReviewKeywordBrief(
        @Field("_id.key")
        val type: String,
        @Field("_id.value")
        val value: String,
        val count: Int
)

data class DocumentReviewBrief(
        @Field("_id.selectable")
        val selectable: String,
        val count: Int
)

data class DocumentReviewRangeCount(
        @Field("_id.range")
        val range: String,
        val count: Long
)