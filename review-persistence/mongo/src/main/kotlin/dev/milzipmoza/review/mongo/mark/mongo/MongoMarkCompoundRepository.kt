package dev.milzipmoza.review.mongo.mark.mongo

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Repository


interface MongoMarkCompoundRepository {
    fun countMarked(book: DocumentMarkBook, type: String): Long
}

@Repository
class MongoMarkCompoundRepositoryImpl(
        private val mongoTemplate: MongoTemplate
) : MongoMarkCompoundRepository {

    /**
     * db.marks.aggregate([
     *   { $lookup: { from: "marked_marks", localField: "markedObjectId", foreignField: "_id", as : "marked_marks" } },
     *   { $match: { "marked_marks.marked": true } },
     *   { $count: "count" }
     * ])
     */
    override fun countMarked(book: DocumentMarkBook, type: String): Long {
        val lookup = Aggregation.lookup("marked_marks", "markedObjectId", "_id", "marked_marks")
        val match = Aggregation.match(
                Criteria.where("marked_marks.marked").`is`(true)
                        .and("type").`is`(type)
                        .and("book.no").`is`(book.no))
        val count = Aggregation.count().`as`("count")
        val aggregation = Aggregation.newAggregation(lookup, match, count)
        val result = mongoTemplate.aggregate(aggregation, "marks", MarkCounted::class.java)
        val uniqueMappedResult = result.uniqueMappedResult
        return uniqueMappedResult?.count ?: 0
    }
}

data class MarkCounted(
        val count: Long
)