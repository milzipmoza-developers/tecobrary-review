package dev.milzipmoza.review.api.endpoint.search.limit

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate


data class LimitCountSearchBookDto(
        val isbn: String,
        val title: String,
        val publisher: String,
        val author: String,
        val imageUrl: String,
        val description: String,
        @JsonFormat(pattern = "yyyy-MM-dd")
        val publishDate: LocalDate,
        val tags: List<ReviewSearchBookTag> = emptyList()
)

data class ReviewSearchBookTag(
        val name: String,
        val colorCode: String
)

