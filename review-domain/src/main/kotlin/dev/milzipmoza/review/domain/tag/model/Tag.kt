package dev.milzipmoza.review.domain.tag.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName

class Tag(
        val no: String = "",
        val color: TagColor,
        val name: TagName,
        val description: TagDescription
) : Entity<Tag> {

    override fun getId() = no

    fun edit(color: TagColor): Tag {
        return Tag(
                no = this.no,
                color = color,
                name = this.name,
                description = this.description,
        )
    }

    fun edit(description: TagDescription): Tag {
        return Tag(
                no = this.no,
                color = this.color,
                name = this.name,
                description = description,
        )
    }
}