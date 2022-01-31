package dev.milzipmoza.review.mongo.mark.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.LookupOperation
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

private const val COLLECTION_NAME = "marks"

@Repository
interface MongoMarkRepository : MongoRepository<DocumentMark, ObjectId> {

    fun findByMemberAndBookAndType(member: DocumentMarkMember, book: DocumentMarkBook, type: String): DocumentMark?

    fun findAllByMemberAndBook(member: DocumentMarkMember, book: DocumentMarkBook): List<DocumentMark>

    fun findAllByMemberAndBook(member: DocumentMarkMember, book: DocumentMarkBook, page: Pageable): Page<DocumentMark>

    fun findAllByMember(member: DocumentMarkMember, page: Pageable): Page<DocumentMark>

    fun findAllByBook(book: DocumentMarkBook, page: Pageable): Page<DocumentMark>
}

@Document(collection = COLLECTION_NAME)
data class DocumentMark(
        @Id val id: ObjectId = ObjectId.get(),
        val type: String,
        val member: DocumentMarkMember,
        val book: DocumentMarkBook,
        val markedObjectId: ObjectId
)

data class DocumentMarkMember(
        @Indexed(unique = true) val no: String
)

data class DocumentMarkBook(
        @Indexed(unique = true) val no: String
)