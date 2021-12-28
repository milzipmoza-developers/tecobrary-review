package dev.milzipmoza.review.api.endpoint.category.action

import dev.milzipmoza.review.domain.category.model.Category
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl

data class CreateCategoryDto(
        val name: String,
        val description: String,
        val imageUrl: String
) {

    fun toModel() = Category(
            name = CategoryName(name = name),
            description = CategoryDescription(description = description),
            imageUrl = CategoryImageUrl(fullUrl = imageUrl)
    )
}