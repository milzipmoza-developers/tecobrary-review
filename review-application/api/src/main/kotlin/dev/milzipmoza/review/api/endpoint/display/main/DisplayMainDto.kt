package dev.milzipmoza.review.api.endpoint.display.main

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.category.model.Category
import java.time.LocalDate

data class DisplayMainInterestBookSectionDto(
        @JsonFormat(pattern = "yyyy-MM-dd")
        val updateDate: LocalDate,
        val books: List<DisplayMainInterestBookDto>
)

data class DisplayMainInterestBookDto(
        val isbn: String,
        val imageUrl: String,
        val tags: List<DisplayMainTagDto>
)

data class DisplayMainTagDto(
        val name: String,
        val colorCode: String
)
