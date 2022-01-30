package dev.milzipmoza.review.api.endpoint.display.book

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class DisplayBookDto(
        val book: DisplayBookDetailDto,
        val mark: DisplayBookMarkDto,
        val category: DisplayBookCategoryDto?,
        val tags: List<DisplayBookTagDto>
)

data class DisplayBookDetailDto(
        val isbn: String,
        val imageUrl: String,
        val title: String,
        val publisher: String,
        val author: String,
        val locale: String,
        @JsonFormat(pattern = "yyyy-MM-dd")
        val publishDate: LocalDate,
        val description: String
)

data class DisplayBookCategoryDto(
        val no: String,
        val imageUrl: String
)

data class DisplayBookMarkDto(
        val like: DisplayBookLikeDto,
        val favorite: DisplayBookFavoriteMarkDto,
)

data class DisplayBookLikeDto(
        val liked: Boolean,
        val counts: Long
)

data class DisplayBookFavoriteMarkDto(
        val marked: Boolean,
        val counts: Long
)

data class DisplayBookTagDto(
        val name: String,
        val colorCode: String
)

