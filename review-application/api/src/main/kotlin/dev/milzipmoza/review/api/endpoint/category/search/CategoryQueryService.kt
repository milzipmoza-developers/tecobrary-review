package dev.milzipmoza.review.api.endpoint.category.search

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.endpoint.category.search.CategoryDto
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

    fun getCategories(page: Int, size: Int, keyword: String?): PageData<CategoryDto> {
        val categories = when (keyword) {
            null -> categories.findAllBy(PageQuery(page = page, size = size))
            else -> categories.findAllBy(keyword, PageQuery(page, size))
        }
        return PageData.of(categories, CategoryDto::of)
    }
}