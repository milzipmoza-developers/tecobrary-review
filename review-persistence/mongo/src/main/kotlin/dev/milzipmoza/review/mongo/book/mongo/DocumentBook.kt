package dev.milzipmoza.review.mongo.book.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoBookRepository : MongoRepository<DocumentBook, ObjectId>, CustomMongoBookRepository {

    fun findByIsbn(isbn: String): DocumentBook?

    fun findAllByIsbnIn(isbn: Iterable<String>): List<DocumentBook>

    fun findAllByDetailMappingIdIn(id: Iterable<ObjectId>): List<DocumentBook>
}

interface CustomMongoBookRepository {

    fun findAllByCategoryNo(categoryNo: String, pageRequest: PageRequest): Page<DocumentBook>
}

@Repository
class CustomMongoBookRepositoryImpl(
        private val mongoTemplate: MongoTemplate,
        private val mongoOperations: MongoOperations
) : CustomMongoBookRepository {

    override fun findAllByCategoryNo(categoryNo: String, pageRequest: PageRequest): Page<DocumentBook> {
        val criteria = Criteria.where("category.no").`is`(categoryNo)

        val query = Query.query(criteria)
                .with(pageRequest)

        val count = mongoOperations.count(query, DocumentBook::class.java)
        val results = mongoTemplate.find(query, DocumentBook::class.java)

        return PageImpl(results, pageRequest, count)
    }
}

@Document(collection = "books")
data class DocumentBook(
        @Id val id: ObjectId = ObjectId(),
        @Indexed(unique = true) val isbn: String,
        var category: DocumentBookCategory?,
        val tagsMappingId: ObjectId,
        val detailMappingId: ObjectId,
)

data class DocumentBookCategory(
        val no: String,
        val name: String,
        val image: DocumentBookCategoryImage
)

data class DocumentBookCategoryImage(
        val host: String,
        val path: String
)