package dev.milzipmoza.review.domain.book.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.book.model.detail.BookDetail

class Book(
        val isbn: String,
        val detail: BookDetail
) : Entity<Book> {

    override fun getId() = isbn

    internal fun edit(bookDetail: BookDetail): Book {
        return Book(
                isbn = this.isbn,
                detail = this.detail.edit(bookDetail),
        )
    }
}