package dev.milzipmoza.review.domain.category

import dev.milzipmoza.review.domain.category.model.Category

interface CategoryOperation {

    fun save(category: Category): String

    fun update(no: String, category: Category): String
}