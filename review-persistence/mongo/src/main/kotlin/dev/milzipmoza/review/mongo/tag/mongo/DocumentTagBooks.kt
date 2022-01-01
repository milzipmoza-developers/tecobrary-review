package dev.milzipmoza.review.mongo.tag.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.query.MongoEntityInformation
import org.springframework.stereotype.Repository

@Repository
interface MongoTagBooksRepository : MongoRepository<DocumentTagBooks, ObjectId>, CustomMongoTagBooksRepository

interface CustomMongoTagBooksRepository {

    fun findAllOfContainBook(bookNo: String, pageRequest: PageRequest): Page<DocumentTagBooks>
}

@Document(collection = COLLECTION_NAME)
data class DocumentTagBooks(
        @Id val id: ObjectId = ObjectId.get(),
        val books: List<String> = listOf()
)

@Repository
class CustomMongoTagBooksRepositoryImpl(
        private val mongoTemplate: MongoTemplate,
        private val mongoOperations: MongoOperations
) : CustomMongoTagBooksRepository{

    override fun findAllOfContainBook(bookNo: String, pageRequest: PageRequest): Page<DocumentTagBooks> {
        val criteria = Criteria.where("books")
                .all(bookNo)

        val query = Query.query(criteria)
                .limit(pageRequest.pageSize)
                .skip(pageRequest.offset)

        val count = mongoOperations.count(query, COLLECTION_NAME)
        val results = mongoTemplate.find(query, DocumentTagBooks::class.java)

        return PageImpl(results, pageRequest, count)
    }
}

private const val COLLECTION_NAME = "tag_books"