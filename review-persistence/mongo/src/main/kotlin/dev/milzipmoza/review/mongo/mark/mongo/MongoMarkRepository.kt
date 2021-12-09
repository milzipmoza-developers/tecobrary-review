package dev.milzipmoza.review.mongo.mark.mongo

import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoMarkRepository : MongoRepository<DocumentMark, ObjectId> {

    fun findByMemberAndBookAndType(member: DocumentMarkMember, book: DocumentMarkBook, type: String): DocumentMark?

    fun findAllByMemberAndBook(member: DocumentMarkMember, book: DocumentMarkBook): List<DocumentMark>

    fun findAllByMember(member: DocumentMarkMember, page : Pageable): Page<DocumentMark>

    fun findAllByBook(book: DocumentMarkBook, page : Pageable): Page<DocumentMark>
}