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

    override fun update(category: Category): String {
        val objectId = ObjectId(category.no)

        return when (mongoCategoryRepository.existsById(objectId)) {
            true -> mongoCategoryRepository.save(DocumentCategoryMapper.map(objectId, category)).id.toHexString()
            false -> throw IllegalArgumentException("카테고리 넘버를 확인해주세요.")
        }
    }
}