package dev.milzipmoza.review.api.endpoint.tag.search

import dev.milzipmoza.review.domain.tag.model.Tag

data class TagDto(
        val no: String,
        val colorCode: String,
        val name: String,
        val description: String
) {
    companion object {
        fun of(tag: Tag): TagDto {
            return TagDto(
                    no = tag.no,
                    colorCode = tag.color.code,
                    name = tag.name.name,
                    description = tag.description.description
            )
        }
    }
}
