package dev.milzipmoza.review.api.endpoint.display.main.dto

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.category.model.Category
import java.time.LocalDate

data class DisplayMainDto(
        val version: String = "1",
        val news: DisplayMainNewBookSectionDto,
        val interests: DisplayMainInterestBookSectionDto,
        val categories: List<DisplayMainCategoryDto>
)

data class DisplayMainNewBookSectionDto(
        @JsonFormat(pattern = "yyyy-MM-dd")
        val updateDate: LocalDate,
        val books: List<DisplayMainNewBookDto>,
)

data class DisplayMainNewBookDto(
        val isbn: String,
        val imageUrl: String,
        val author: String,
        val title: String
) {
        companion object {
                fun of(book: Book): DisplayMainNewBookDto{
                        return DisplayMainNewBookDto(
                                isbn = book.isbn,
                                imageUrl = book.detail.fullImageUrl,
                                author = book.detail.author,
                                title = book.detail.title
                        )
                }
        }
}

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

data class DisplayMainCategoryDto(
        val no: String,
        val name: String,
        val imageUrl: String
) {
        companion object {
                fun of(category: Category): DisplayMainCategoryDto {
                        return DisplayMainCategoryDto(
                                no = category.no,
                                name = category.name.name,
                                imageUrl = category.fullImageUrl
                        )
                }
        }
}