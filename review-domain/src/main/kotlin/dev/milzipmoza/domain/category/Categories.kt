package dev.milzipmoza.domain.category

import dev.milzipmoza.domain.category.model.Category

interface Categories {

    fun findBy(no: String): Category
}