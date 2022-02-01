package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.domain.category.model.Category


data class DisplayMainCategoryDto(
        val no: String,
        val name: String,
        val imageUrl: String
) {
    companion object {
        fun of(category: Category): DisplayMainCategoryDto {
            return DisplayMainCategoryDto(
                    no = category.no,
                    name = category.name.name,
                    imageUrl = category.fullImageUrl
            )
        }
    }
}
