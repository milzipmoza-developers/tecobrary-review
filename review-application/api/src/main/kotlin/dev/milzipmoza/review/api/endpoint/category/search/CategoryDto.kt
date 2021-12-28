package dev.milzipmoza.review.api.endpoint.category.search

import dev.milzipmoza.review.domain.category.model.Category

data class CategoryDto(
        val no: String,
        val name: String,
        val description: String,
        val imageUrl: String
) {
    companion object {
        fun of(category: Category) = CategoryDto(
                no = category.no,
                name = category.name.name,
                description = category.description.description,
                imageUrl = category.fullImageUrl
        )
    }
}