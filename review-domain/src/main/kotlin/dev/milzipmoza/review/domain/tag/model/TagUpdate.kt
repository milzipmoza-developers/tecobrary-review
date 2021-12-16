package dev.milzipmoza.review.domain.tag.model

import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName

class TagUpdate(
        val tag: Tag
) {
    fun doUpdate(colorCode: String, description: String): Tag {
        val tagColor = when (colorCode.isBlank()) {
            true -> tag.color
            false -> TagColor(colorCode)
        }

        val tagDescription = when (description.isBlank()) {
            true -> tag.description
            false -> TagDescription(description)
        }

        return tag.edit(tagColor)
                .edit(tagDescription)
    }
}