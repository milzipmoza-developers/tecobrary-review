package dev.milzipmoza.domain.category

import dev.milzipmoza.domain.Entity
import dev.milzipmoza.domain.category.color.CategoryColor
import dev.milzipmoza.domain.category.description.CategoryDescription
import dev.milzipmoza.domain.category.name.CategoryName

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