package dev.milzipmoza.review.api.endpoint.display.main

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.domain.book.model.Book
import java.time.LocalDate

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
        fun of(book: Book): DisplayMainNewBookDto {
            return DisplayMainNewBookDto(
                    isbn = book.isbn,
                    imageUrl = book.detail.fullImageUrl,
                    author = book.detail.author,
                    title = book.detail.summarizedTitle
            )
        }
    }
}