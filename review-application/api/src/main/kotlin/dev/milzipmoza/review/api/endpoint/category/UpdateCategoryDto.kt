package dev.milzipmoza.review.api.endpoint.category

data class UpdateCategoryDto(
        val colorCode: String,
        val description: String,
        val imageUrl: String
)
