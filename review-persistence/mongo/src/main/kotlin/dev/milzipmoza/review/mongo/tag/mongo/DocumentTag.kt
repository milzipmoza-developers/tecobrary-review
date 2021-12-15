package dev.milzipmoza.review.mongo.tag.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoTagRepository : MongoRepository<DocumentTag, ObjectId> {

    fun findByName(name: String): DocumentTag?

    fun findAllByBookMappingIdIn(ids: Iterable<ObjectId>, pageRequest: PageRequest): Page<DocumentTag>
}

@Document(collection = "tags")
data class DocumentTag(
        @Id val id: ObjectId = ObjectId.get(),
        @Indexed(unique = true) val colorCode: String,
        @Indexed(unique = true) val name: String,
        val description: String,
        val bookMappingId: ObjectId
)