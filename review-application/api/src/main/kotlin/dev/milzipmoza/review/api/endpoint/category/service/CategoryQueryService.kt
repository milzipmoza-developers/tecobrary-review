package dev.milzipmoza.review.api.endpoint.category.service

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.endpoint.category.CategoryDto
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.mongo.category.domain.MongoCategories

@ApplicationService
class CategoryQueryService(
        private val mongoCategories: MongoCategories
) {

    fun getCategory(no: String): CategoryDto {
        val category = mongoCategories.findBy(no)
        return CategoryDto.of(category)
    }

    fun getCategories(page: Int, size: Int): PageData<CategoryDto> {
        val categories = mongoCategories.findAllBy(PageQuery(page = page, size = size))
        return PageData.of(categories, CategoryDto::of)
    }
}