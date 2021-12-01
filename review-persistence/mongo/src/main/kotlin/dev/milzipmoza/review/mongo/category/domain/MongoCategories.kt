package dev.milzipmoza.review.mongo.category.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.category.Categories
import dev.milzipmoza.review.domain.category.model.Category
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.category.mongo.DocumentCategoryMapper
import dev.milzipmoza.review.mongo.category.mongo.MongoCategoryRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class MongoCategories(
        private val mongoCategoryRepository: MongoCategoryRepository
) : Categories {

    override fun findBy(no: String): Category {
        val document = mongoCategoryRepository.findById(ObjectId(no))
                ?: throw DocumentNotFoundException("카테고리 넘버를 확인해주세요.")

        return DocumentCategoryMapper.map(document)
    }

    override fun findAllBy(pageQuery: PageQuery): PageEntities<Category> {
        val documents = mongoCategoryRepository.findAll(PageRequest.of(pageQuery.page, pageQuery.size))

        return PageEntities(
                total = documents.totalElements,
                size = documents.size,
                isFirst = documents.isFirst,
                isLast = documents.isLast,
                items = documents.content
                        .map { DocumentCategoryMapper.map(it) }
                        .toList()
        )
    }
}