package dev.milzipmoza.review.domain.category.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl

class Category(
        val no: String = "",
        val name: CategoryName,
        val description: CategoryDescription,
        val imageUrl: CategoryImageUrl
) : Entity<Category> {
    val fullImageUrl: String
        get() = imageUrl.toUrl()

    override fun getId() = no

    fun edit(imageUrl: CategoryImageUrl): Category {
        return Category(
                no = this.no,
                name = this.name,
                description = this.description,
                imageUrl = imageUrl
        )
    }

    fun edit(description: CategoryDescription): Category {
        return Category(
                no = this.no,
                name = this.name,
                description = description,
                imageUrl = this.imageUrl
        )
    }
}