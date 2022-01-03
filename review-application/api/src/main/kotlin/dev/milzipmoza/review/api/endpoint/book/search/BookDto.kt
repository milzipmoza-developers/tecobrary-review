package dev.milzipmoza.review.api.endpoint.book.search

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.tag.BookTags
import java.time.LocalDate

data class BookDto(
        val isbn: String,
        val detail: BookDetailDto,
        val category: BookCategoryDto?,
        val tags: List<BookTagDto>
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

data class BookCategoryDto(
        val no: String,
        val name: String,
        val imageUrl: String
)

data class BookTagDto(
        val no: String,
        val name: String,
        val colorCode: String
)

object BookDtoMapper {
    fun map(book: Book) = BookDto(
            isbn = book.isbn,
            detail = map(book.detail),
            category = map(book.category),
            tags = map(book.tags)
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

    private fun map(bookCategory: BookCategory) = when (bookCategory) {
        is BookCategory.EnrolledBookCategory -> {
            BookCategoryDto(
                    no = bookCategory.no,
                    name = bookCategory.name,
                    imageUrl = bookCategory.fullImageUrl
            )
        }
        is BookCategory.NoBookCategory -> null
    }

    private fun map(bookTags: BookTags) = bookTags.map { BookTagDto(it.no, it.name, it.colorCode) }.toList()
}