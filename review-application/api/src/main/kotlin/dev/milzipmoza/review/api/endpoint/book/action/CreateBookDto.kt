package dev.milzipmoza.review.api.endpoint.book.action

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl
import java.time.LocalDate

data class CreateBookDto(
        val isbn: String,
        val imageUrl: String,
        val title: String,
        val publisher: String,
        val author: String,
        @JsonFormat(pattern = "yyyy-MM-dd")
        val publishDate: LocalDate,
        val description: String
)

object CreateBookDtoMapper {
    fun map(createBookDto: CreateBookDto) =
            Book(
                    isbn = createBookDto.isbn,
                    detail = BookDetail(
                            image = BookImageUrl(createBookDto.imageUrl),
                            title = createBookDto.title,
                            publisher = createBookDto.publisher,
                            author = createBookDto.author,
                            publishDate = createBookDto.publishDate,
                            description = createBookDto.description
                    )
            )
}