package dev.milzipmoza.review.mongo.mark.mongo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoMarkRepository : MongoRepository<DocumentMark, ObjectId> {

    fun findByMemberAndBookAndType(member: DocumentMarkMember, book: DocumentMarkBook, type: String): DocumentMark?

    fun findAllByMemberAndBook(member: DocumentMarkMember, book: DocumentMarkBook): List<DocumentMark>
}