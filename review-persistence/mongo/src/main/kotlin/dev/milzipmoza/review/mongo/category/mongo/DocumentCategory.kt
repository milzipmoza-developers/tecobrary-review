package dev.milzipmoza.review.mongo.category.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.SampleOperation
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.data.mongodb.core.query.TextQuery
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Document(collection = COLLECTION_NAME)
data class DocumentCategory(
        @Id
        val id: ObjectId = ObjectId.get(),
        @Indexed(unique = true)
        @TextIndexed
        val name: String,
        val description: String,
        val image: DocumentCategoryImage
)

@Repository
interface MongoCategoryRepository : MongoRepository<DocumentCategory, String>, CustomMongoCategoryRepository {

    fun findById(id: ObjectId): DocumentCategory?

    fun existsById(id: ObjectId): Boolean
}

interface CustomMongoCategoryRepository {

    fun findAllByKeyword(keyword: String, pageRequest: PageRequest): Page<DocumentCategory>

    fun getRandom(count: Long): List<DocumentCategory>
}

@Repository
class CustomMongoCategoryRepositoryImpl(
        private val mongoTemplate: MongoTemplate,
        private val mongoOperations: MongoOperations
)  : CustomMongoCategoryRepository {

    override fun findAllByKeyword(keyword: String, pageRequest: PageRequest): Page<DocumentCategory> {
        val criteria = TextCriteria.forDefaultLanguage()
                .matchingAny(keyword)

        val query = TextQuery.queryText(criteria)
                .sortByScore()
                .with(pageRequest)

        val count = mongoOperations.count(query, COLLECTION_NAME)
        val results = mongoTemplate.find(query, DocumentCategory::class.java)

        return PageImpl(results, pageRequest, count)
    }

    override fun getRandom(count: Long): List<DocumentCategory> {
        val sampleOperation = SampleOperation(count)
        val aggregation = Aggregation.newAggregation(sampleOperation)
        val aggregationResults = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, DocumentCategory::class.java)
        return aggregationResults.mappedResults
    }
}

private const val COLLECTION_NAME = "categories"