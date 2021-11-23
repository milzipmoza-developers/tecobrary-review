package dev.milzipmoza.review.api.endpoint.category

import dev.milzipmoza.review.domain.category.model.Category
import dev.milzipmoza.review.domain.category.model.color.CategoryColor
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl
import dev.milzipmoza.review.mongo.category.mongo.DocumentCategoryImage

data class CreateCategoryRequest(
        val colorCode: String,
        val name: String,
        val description: String,
        val imageUrl: String
) {

    fun toModel() = Category(
            color = CategoryColor(code = colorCode),
            name = CategoryName(name = name),
            description = CategoryDescription(description = description),
            imageUrl = CategoryImageUrl(fullUrl = imageUrl)
    )
}