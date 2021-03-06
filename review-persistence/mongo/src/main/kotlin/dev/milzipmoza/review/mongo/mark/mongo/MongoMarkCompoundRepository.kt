package dev.milzipmoza.review.mongo.mark.mongo

import dev.milzipmoza.review.domain.mark.MarkType
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Repository
import java.time.LocalDateTime


interface MongoMarkCompoundRepository {
    fun isMarked(member: DocumentMarkMember, book: DocumentMarkBook, type: String): Boolean

    fun countMarked(book: DocumentMarkBook, type: String): Long

    fun getRecentFavoriteAfter(member: DocumentMarkMember, size: Long, beforeMarkDateTime: LocalDateTime?): List<DocumentBookmark>
}

@Repository
class MongoMarkCompoundRepositoryImpl(
        private val mongoTemplate: MongoTemplate
) : MongoMarkCompoundRepository {

    /**
     * db.marks.aggregate([
     *   { $lookup: { from: "marked_marks", localField: "markedObjectId", foreignField: "_id", as : "marked_marks" } },
     *   { $match: { $and: [
     *     {"member.no": "61f637c55f2e966c0c00f924"},
     *     {"book.no": "9788966263370"},
     *     {"type": "LIKE"}
     *   ]} },
     *   { $project: {"marked": "$marked_marks.marked", _id: 0 } },
     *   { $unwind: "$marked" }
     * ])
     */
    override fun isMarked(member: DocumentMarkMember, book: DocumentMarkBook, type: String): Boolean {
        val lookup = Aggregation.lookup("marked_marks", "markedObjectId", "_id", "marked_marks")
        val match = Aggregation.match(Criteria.where("member.no").`is`(member.no)
                .and("book.no").`is`(book.no)
                .and("type").`is`(type))
        val project = Aggregation.project("marked_marks.marked")
        val unwind = Aggregation.unwind("marked")
        val aggregation = Aggregation.newAggregation(lookup, match, project, unwind)

        val result = mongoTemplate.aggregate(aggregation, DocumentMark::class.java, MarkMarked::class.java)
        val uniqueMappedResult = result.uniqueMappedResult
        return uniqueMappedResult?.marked ?: false
    }

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

        val result = mongoTemplate.aggregate(aggregation, DocumentMark::class.java, MarkCounted::class.java)
        val uniqueMappedResult = result.uniqueMappedResult
        return uniqueMappedResult?.count ?: 0
    }

    override fun getRecentFavoriteAfter(member: DocumentMarkMember, size: Long, beforeMarkDateTime: LocalDateTime?): List<DocumentBookmark> {
        val lookup = Aggregation.lookup("marked_marks", "markedObjectId", "_id", "marked_marks")
        val match = when (beforeMarkDateTime) {
            null -> Aggregation.match(
                    Criteria.where("marked_marks.marked").`is`(true)
                            .and("member.no").`is`(member.no)
                            .and("type").`is`(MarkType.FAVORITE.name))
            else -> Aggregation.match(
                    Criteria.where("marked_marks.marked").`is`(true)
                            .and("member.no").`is`(member.no)
                            .and("type").`is`(MarkType.FAVORITE.name)
                            .and("marked_marks.txDateTime").lt(beforeMarkDateTime))
        }
        val sort = Aggregation.sort(Sort.Direction.DESC, "marked_marks.txDateTime")
        val limit = Aggregation.limit(size)
        val project = Aggregation.project("_id", "member", "book", "marked_marks.txDateTime")
        val unwind = Aggregation.unwind("txDateTime")

        val aggregation = Aggregation.newAggregation(lookup, match, sort, limit, project, unwind)

        val results = mongoTemplate.aggregate(aggregation, DocumentMark::class.java, DocumentBookmark::class.java)
        return results.mappedResults
    }
}

data class MarkCounted(
        val count: Long
)

data class MarkMarked(
        val marked: Boolean
)