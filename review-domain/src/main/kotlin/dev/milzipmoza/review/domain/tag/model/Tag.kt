package dev.milzipmoza.review.domain.tag.model

import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName

class Tag(
        val no: String = "",
        val color: TagColor,
        val name: TagName,
        val description: TagDescription
) {
}