package dev.milzipmoza.review.api.endpoint.category

import dev.milzipmoza.review.domain.category.model.Category

data class CategoryDto(
        val no: String,
        val colorCode: String,
        val name: String,
        val description: String,
        val imageUrl: String
) {
    companion object {
        fun of(category: Category) = CategoryDto(
                no = category.no,
                colorCode = category.color.code,
                name = category.name.name,
                description = category.description.description,
                imageUrl = category.fullImageUrl
        )
    }
}