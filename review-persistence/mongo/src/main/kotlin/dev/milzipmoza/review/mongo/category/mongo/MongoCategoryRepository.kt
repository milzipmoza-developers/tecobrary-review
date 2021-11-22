package dev.milzipmoza.review.mongo.category.mongo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoCategoryRepository : MongoRepository<DocumentCategory, String> {

    fun findById(id: ObjectId): DocumentCategory?

    fun existsById(id: ObjectId): Boolean
}