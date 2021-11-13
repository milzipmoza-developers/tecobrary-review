package dev.milzipmoza.domain.category

import dev.milzipmoza.domain.category.model.Category

interface CategoryOperation {

    fun save(category: Category)

    fun update(category: Category)
}