package dev.milzipmoza.review.api.endpoint.review


data class ReviewSubmitDto(
        val isbn: String,
        val range: String,
        val content: String,
        val informative: String,
        val readMore: String? = null,
        val selectables: Set<String> = emptySet()
)
