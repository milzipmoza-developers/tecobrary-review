package dev.milzipmoza.review.mongo.tag.mongo

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

@Document(collection = COLLECTION_NAME)
data class DocumentTag(
        @Id val id: ObjectId = ObjectId.get(),
        @Indexed(unique = true) val colorCode: String,
        @Indexed(unique = true) val name: String,
        val description: String
)

@Repository
interface MongoTagRepository : MongoRepository<DocumentTag, ObjectId>, CustomMongoTagRepository {

    fun findByName(name: String): DocumentTag?

    fun findAllByIdIn(objectId: List<ObjectId>): List<DocumentTag>
}

interface CustomMongoTagRepository {

    fun findByNameExceptTagNos(name: String, exceptTagNos: List<String>): DocumentTag?

    fun findAllExceptTagNos(pageRequest: PageRequest, exceptTagNos: List<String>): Page<DocumentTag>
}

@Repository
class CustomMongoTagRepositoryImpl(
        private val mongoTemplate: MongoTemplate,
        private val mongoOperations: MongoOperations
) : CustomMongoTagRepository {

    override fun findByNameExceptTagNos(name: String, exceptTagNos: List<String>): DocumentTag? {

        val query = Query().apply {
            addCriteria(Criteria.where("name").`is`(name))
            addCriteria(Criteria.where("no").nin(exceptTagNos))
        }

        return mongoTemplate.findOne(query, DocumentTag::class.java)
    }

    override fun findAllExceptTagNos(pageRequest: PageRequest, exceptTagNos: List<String>): Page<DocumentTag> {

        val query = Query().apply {
            addCriteria(Criteria.where("no").nin(exceptTagNos))
            with(pageRequest)
        }

        val count = mongoOperations.count(query, COLLECTION_NAME)
        val results = mongoTemplate.find(query, DocumentTag::class.java)

        return PageImpl(results, pageRequest, count)
    }
}

private const val COLLECTION_NAME = "tags"