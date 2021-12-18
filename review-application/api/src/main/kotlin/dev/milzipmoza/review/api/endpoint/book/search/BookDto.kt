package dev.milzipmoza.review.api.endpoint.book.search

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import java.time.LocalDate

data class BookDto(
        val isbn: String,
        val detail: BookDetailDto
)

data class BookDetailDto(
        val imageUrl: String,
        val title: String,
        val publisher: String,
        val author: String,
        val locale: String,
        @JsonFormat(pattern = "yyyy-MM-dd")
        val publishDate: LocalDate,
        val description: String
)

object BookDtoMapper {
    fun map(book: Book) = BookDto(
            isbn = book.isbn,
            detail = map(book.detail)
    )

    private fun map(bookDetail: BookDetail) = BookDetailDto(
            imageUrl = bookDetail.image.toUrl(),
            title = bookDetail.title,
            publisher = bookDetail.publisher,
            author = bookDetail.author,
            locale = bookDetail.locale.name,
            publishDate = bookDetail.publishDate,
            description = bookDetail.description
    )
}