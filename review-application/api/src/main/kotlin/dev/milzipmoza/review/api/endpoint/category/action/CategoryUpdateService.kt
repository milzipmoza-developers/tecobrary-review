package dev.milzipmoza.review.api.endpoint.category.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.category.Categories
import dev.milzipmoza.review.domain.category.CategoryUpdate
import dev.milzipmoza.review.domain.category.CategoryOperation

@ApplicationService
class CategoryUpdateService(
        private val categories: Categories,
        private val categoryOperation: CategoryOperation
) {

    fun doUpdate(categoryNo: String, updateCategoryDto: UpdateCategoryDto): String {
        val category = categories.findBy(categoryNo)

        val command = CategoryUpdate(category)

        val updatedCategory = command.doUpdate(
                description = updateCategoryDto.description,
                imageUrl = updateCategoryDto.imageUrl
        )

        return categoryOperation.update(categoryNo, updatedCategory)
    }
}