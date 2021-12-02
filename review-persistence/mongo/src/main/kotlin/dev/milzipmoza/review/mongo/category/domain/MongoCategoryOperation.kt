package dev.milzipmoza.review.mongo.category.domain

import dev.milzipmoza.review.domain.category.CategoryOperation
import dev.milzipmoza.review.domain.category.model.Category
import dev.milzipmoza.review.mongo.category.mongo.DocumentCategoryMapper
import dev.milzipmoza.review.mongo.category.mongo.MongoCategoryRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoCategoryOperation(
        private val mongoCategoryRepository: MongoCategoryRepository
) : CategoryOperation {

    override fun save(category: Category): String {
        val document = DocumentCategoryMapper.map(category)

        return mongoCategoryRepository.insert(document).id.toHexString()
    }

    override fun update(no: String, category: Category): String {
        val objectId = ObjectId(no)

        return mongoCategoryRepository.save(DocumentCategoryMapper.map(objectId, category)).id.toHexString()
    }
}