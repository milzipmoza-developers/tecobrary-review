package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl


data class UpdateBookDto(
        val imageUrl: String,
        val title: String,
        val publisher: String,
        val author: String,
        val description: String
)

object UpdateBookDtoMapper {

    fun map(update: UpdateBookDto, book: Book) =
            BookDetail(
                    image = BookImageUrl(update.imageUrl),
                    title = update.title,
                    publisher = update.publisher,
                    author = update.author,
                    publishDate = book.detail.publishDate,
                    description = update.description
            )
}