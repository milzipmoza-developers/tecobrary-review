package dev.milzipmoza.review.domain.category

import dev.milzipmoza.review.domain.category.model.Category
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl

class CategoryUpdate(
        val category: Category
) {

    fun doUpdate(
            description: String,
            imageUrl: String
    ): Category {
        val categoryImageUrl = when (imageUrl.isBlank()) {
            true -> category.imageUrl
            false -> CategoryImageUrl(imageUrl)
        }

        val categoryDescription = when (description.isBlank()) {
            true -> category.description
            false -> CategoryDescription(description)
        }

        return category.edit(categoryImageUrl)
                .edit(categoryDescription)
    }
}