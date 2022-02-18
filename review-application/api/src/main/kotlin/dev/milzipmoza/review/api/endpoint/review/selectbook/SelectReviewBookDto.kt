package dev.milzipmoza.review.api.endpoint.review.selectbook

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class SelectReviewBookDto(
        val isbn: String,
        val title: String,
        val publisher: String,
        val author: String,
        val imageUrl: String,
        val description: String,
        @JsonFormat(pattern = "yyyy-MM-dd")
        val publishDate: LocalDate,
)
