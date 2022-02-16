package dev.milzipmoza.review.mongo.book.mongo

import java.time.LocalDate
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoBookRepository : MongoRepository<DocumentBook, ObjectId>, CustomMongoBookRepository {

    fun findByIsbn(isbn: String): DocumentBook?

    fun findAllByIsbnIn(isbn: Iterable<String>): List<DocumentBook>
}

interface CustomMongoBookRepository {

    fun findAllByCategoryNo(categoryNo: String, pageRequest: PageRequest): Page<DocumentBook>

    fun findAllAfterPublishDate(baseDate: LocalDate, pageRequest: PageRequest): Page<DocumentBook>
}

@Repository
class CustomMongoBookRepositoryImpl(
        private val mongoTemplate: MongoTemplate,
        private val mongoOperations: MongoOperations
) : CustomMongoBookRepository {

    override fun findAllByCategoryNo(categoryNo: String, pageRequest: PageRequest): Page<DocumentBook> {
        val criteria = Criteria.where("category.no").`is`(categoryNo)

        val query = Query.query(criteria)

        val count = mongoOperations.count(query, DocumentBook::class.java)
        val results = mongoTemplate.find(query.with(pageRequest), DocumentBook::class.java)

        return PageImpl(results, pageRequest, count)
    }


    override fun findAllAfterPublishDate(baseDate: LocalDate, pageRequest: PageRequest): Page<DocumentBook> {
        val criteria = Criteria.where("detail.publishDate").gte(baseDate)

        val query = Query().addCriteria(criteria)
                .with(pageRequest)
                .with(Sort.by(Sort.Direction.DESC, "detail.publishDate"))

        val count = mongoOperations.count(query, DocumentBook::class.java)
        val results = mongoTemplate.find(query, DocumentBook::class.java)

        return PageImpl(results, pageRequest, count)
    }
}