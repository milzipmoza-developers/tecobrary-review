package dev.milzipmoza.review.api.endpoint.category.service

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.endpoint.category.UpdateCategoryDto
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
                colorCode = updateCategoryDto.colorCode,
                description = updateCategoryDto.description,
                imageUrl = updateCategoryDto.imageUrl
        )

        return categoryOperation.update(categoryNo, updatedCategory)
    }
}