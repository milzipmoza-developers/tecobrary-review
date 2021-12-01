package dev.milzipmoza.review.api.endpoint.category.service

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.endpoint.category.CreateCategoryDto
import dev.milzipmoza.review.mongo.category.domain.MongoCategoryOperation

@ApplicationService
class CategoryCommandService(
        private val mongoCategoryOperation: MongoCategoryOperation
) {

    fun createCategory(createCategoryDto: CreateCategoryDto): String {
        val category = createCategoryDto.toModel()
        return mongoCategoryOperation.save(category)
    }
}