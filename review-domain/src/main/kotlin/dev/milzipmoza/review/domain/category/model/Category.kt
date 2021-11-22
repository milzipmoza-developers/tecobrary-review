package dev.milzipmoza.review.domain.category.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.category.model.color.CategoryColor
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl

class Category(
        val no: String = "",
        val color: CategoryColor,
        val name: CategoryName,
        val description: CategoryDescription,
        val imageUrl: CategoryImageUrl
) : Entity<Category> {
    val fullImageUrl: String
        get() = imageUrl.toUrl()

    override fun getId() = no

    fun change(color: CategoryColor): Category {
        return Category(
                no = this.no,
                color = color,
                name = this.name,
                description = this.description,
                imageUrl = this.imageUrl
        )
    }

    fun change(name: CategoryName): Category {
        return Category(
                no = this.no,
                color = this.color,
                name = name,
                description = this.description,
                imageUrl = this.imageUrl
        )
    }

    fun edit(description: CategoryDescription): Category {
        return Category(
                no = this.no,
                color = this.color,
                name = this.name,
                description = description,
                imageUrl = this.imageUrl
        )
    }
}