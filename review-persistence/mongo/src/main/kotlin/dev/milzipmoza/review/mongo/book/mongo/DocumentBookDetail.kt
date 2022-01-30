package dev.milzipmoza.review.mongo.book.mongo

import dev.milzipmoza.review.mongo.category.mongo.CustomMongoCategoryRepository
import java.time.LocalDate
import javax.print.Doc
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

private const val COLLECTION_NAME = "book_details"

@Repository
interface MongoBookDetailRepository : MongoRepository<DocumentBookDetail, ObjectId>, CustomMongoBookDetailRepository {

    fun findAllByIdIn(id: Iterable<ObjectId>): List<DocumentBookDetail>
}

interface CustomMongoBookDetailRepository {

    fun findAllAfterPublishDate(baseDate: LocalDate, count: Int): List<DocumentBookDetail>
}

@Repository
class CustomMongoBookDetailRepositoryImpl(
        private val mongoTemplate: MongoTemplate,
        private val mongoOperations: MongoOperations
) : CustomMongoBookDetailRepository {

    override fun findAllAfterPublishDate(baseDate: LocalDate, count: Int): List<DocumentBookDetail> {
        val criteria = Criteria.where("publishDate").gte(baseDate)

        val query = Query().addCriteria(criteria)
                .limit(count)
                .with(Sort.by(Sort.Direction.DESC, "publishDate"))

        return mongoTemplate.find(query, DocumentBookDetail::class.java, COLLECTION_NAME)
    }
}

@Document(collection = "book_details")
data class DocumentBookDetail(
        val id: ObjectId = ObjectId(),
        val title: String,
        val publisher: String,
        val author: String,
        val image: DocumentBookDetailImage,
        val locale: String,
        val publishDate: LocalDate,
        val description: String
)

class DocumentBookDetailImage(
        val host: String,
        val path: String
)