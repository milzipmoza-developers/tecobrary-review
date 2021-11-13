package dev.milzipmoza.domain.category

interface CategoryOperation {

    fun save(category: Category)

    fun update(category: Category)
}