package dev.milzipmoza.review.mongo.mark.mongo

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Repository

interface MongoMarkAggregator {

    fun findTop(type: String, count: Int): List<DocumentFindTopResult>
}

@Repository
class MongoMarkAggregatorImpl(
        private val mongoTemplate: MongoTemplate
) : MongoMarkAggregator {

    /**
     * db.marks.aggregate([
     *  { $match: {type: "LIKE"} },
     *  { $lookup: { from: "marked_marks", localField: "markedObjectId", foreignField: "_id", as : "marked_marks" } },
     *  { $unwind: "$marked_marks" },
     *  { $group: { _id: { book: "$book.no", marked: "$marked_marks.marked" }, count: {$sum: 1} } },
     *  { $match: {"_id.marked": true}},
     *  { $sort: {count: -1} }
     * ])
     */
    override fun findTop(type: String, count: Int): List<DocumentFindTopResult> {
        val matchType = Aggregation.match(Criteria.where("type").`is`(type))
        val lookup = Aggregation.lookup("marked_marks", "markedObjectId", "_id", "marked_marks")
        val unwind = Aggregation.unwind("marked_marks")
        val group = Aggregation.group("book.no", "marked_marks.marked").count().`as`("count")
        val match = Aggregation.match(Criteria.where("_id.marked").`is`(true))
        val sort = Aggregation.sort(Sort.by(Sort.Direction.DESC, "count"))
        val limit = Aggregation.limit(3)

        val aggregation = Aggregation.newAggregation(matchType, lookup, unwind, group, match, sort, limit)
        val result = mongoTemplate.aggregate(aggregation, DocumentMark::class.java, DocumentFindTopResult::class.java)
        return result.mappedResults
    }
}

data class DocumentFindTopResult(
        @Field("_id.marked")
        val marked: Boolean,
        val count: Int,
        @Field("_id.no")
        val bookNo: String,
)