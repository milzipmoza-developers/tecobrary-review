package dev.milzipmoza.review.domain.category

import dev.milzipmoza.review.domain.category.model.Category

interface CategoryOperation {

    fun save(category: Category)

    fun update(category: Category)
}