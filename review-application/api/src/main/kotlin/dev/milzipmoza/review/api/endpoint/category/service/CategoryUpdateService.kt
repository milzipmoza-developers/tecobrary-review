package dev.milzipmoza.review.api.endpoint.category.service

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.endpoint.category.UpdateCategoryDto
import dev.milzipmoza.review.domain.category.Categories
import dev.milzipmoza.review.domain.category.CategoryOperation
import dev.milzipmoza.review.domain.category.model.color.CategoryColor
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl

@ApplicationService
class CategoryUpdateService(
        private val categories: Categories,
        private val categoryOperation: CategoryOperation
) {

    fun doUpdate(categoryNo: String, updateCategoryDto: UpdateCategoryDto): String {
        val category = categories.findBy(categoryNo)

        val editedCategory = category.edit(CategoryImageUrl(updateCategoryDto.imageUrl))
                .edit(CategoryColor(updateCategoryDto.colorCode))
                .edit(CategoryDescription(updateCategoryDto.description))

        return categoryOperation.update(categoryNo, editedCategory)
    }
}