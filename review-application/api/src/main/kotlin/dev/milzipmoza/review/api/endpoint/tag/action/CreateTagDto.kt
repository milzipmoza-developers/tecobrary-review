package dev.milzipmoza.review.api.endpoint.tag.action

data class CreateTagDto(
        val colorCode: String,
        val name: String,
        val description: String,
)