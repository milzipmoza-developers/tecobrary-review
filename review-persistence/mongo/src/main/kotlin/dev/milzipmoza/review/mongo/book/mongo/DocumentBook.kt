package dev.milzipmoza.review.mongo.book.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoBookRepository : MongoRepository<DocumentBook, ObjectId> {

    fun findByIsbn(isbn: String): DocumentBook?
}

@Document(collection = "books")
data class DocumentBook(
        @Id val id: ObjectId = ObjectId(),
        @Indexed(unique = true) val isbn: String,
        val detailMappingId: ObjectId,
)