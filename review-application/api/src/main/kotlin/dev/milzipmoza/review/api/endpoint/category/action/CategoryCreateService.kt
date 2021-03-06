package dev.milzipmoza.review.api.endpoint.category.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.category.CategoryOperation

@ApplicationService
class CategoryCreateService(
        private val categoryOperation: CategoryOperation
) {

    fun doCreate(createCategoryDto: CreateCategoryDto): String {
        val category = createCategoryDto.toModel()
        return categoryOperation.save(category)
    }
}