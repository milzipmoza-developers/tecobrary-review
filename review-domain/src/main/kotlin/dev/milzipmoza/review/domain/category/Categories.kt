package dev.milzipmoza.review.domain.category

import dev.milzipmoza.review.domain.category.model.Category

interface Categories {

    fun findBy(no: String): Category
}