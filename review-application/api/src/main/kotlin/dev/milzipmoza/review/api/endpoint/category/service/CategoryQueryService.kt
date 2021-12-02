package dev.milzipmoza.review.api.endpoint.category.service

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.endpoint.category.CategoryDto
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.category.Categories

@ApplicationService
class CategoryQueryService(
        private val categories: Categories
) {

    fun getCategory(no: String): CategoryDto {
        val category = categories.findBy(no)
        return CategoryDto.of(category)
    }

    fun getCategories(page: Int, size: Int): PageData<CategoryDto> {
        val categories = categories.findAllBy(PageQuery(page = page, size = size))
        return PageData.of(categories, CategoryDto::of)
    }
}