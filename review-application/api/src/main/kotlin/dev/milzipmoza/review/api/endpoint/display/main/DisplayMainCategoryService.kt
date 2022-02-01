package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.category.Categories

@ApplicationService
class DisplayMainCategoryService(
        private val categories: Categories
) {

    fun getRandomCategories(): List<DisplayMainCategoryDto> {
        return categories.getRandom(DISPLAY_CATEGORY_COUNT)
                .map { DisplayMainCategoryDto.of(it) }
                .toList()
    }

    companion object {
        private const val DISPLAY_CATEGORY_COUNT = 5L
    }
}