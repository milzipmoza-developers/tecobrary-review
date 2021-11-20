package dev.milzipmoza.review.domain.category.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.category.model.color.CategoryColor
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName

class Category(
        val no: String,
        color: CategoryColor,
        name: CategoryName,
        description: CategoryDescription,
        val imagePath: String
) : Entity<Category> {
    var color = color
        private set
    var name = name
        private set
    var description = description
        private set

    override fun getId() = no

    fun change(color: CategoryColor) {
        this.color = color
    }

    fun change(name: CategoryName) {
        this.name = name
    }

    fun edit(description: CategoryDescription) {
        this.description = description
    }
}