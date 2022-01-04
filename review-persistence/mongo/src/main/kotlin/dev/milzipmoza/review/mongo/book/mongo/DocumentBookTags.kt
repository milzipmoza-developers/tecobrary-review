package dev.milzipmoza.review.mongo.book.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoBookTagsRepository: MongoRepository<DocumentBookTags, ObjectId>

@Document(collection = "book_tags")
data class DocumentBookTags(
        @Id val id: ObjectId = ObjectId(),
        val tags: List<DocumentBookTag>
)

data class DocumentBookTag(
        val no: String,
        val name: String,
        val colorCode: String
)