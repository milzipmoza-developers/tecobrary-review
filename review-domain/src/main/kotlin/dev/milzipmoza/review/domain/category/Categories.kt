package dev.milzipmoza.review.domain.category

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.category.model.Category

interface Categories {

    fun findBy(no: String): Category

    fun findAllBy(pageQuery: PageQuery): PageEntities<Category>

    fun isExistBy(no: String): Boolean
}