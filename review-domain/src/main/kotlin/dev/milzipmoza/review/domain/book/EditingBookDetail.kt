package dev.milzipmoza.review.domain.book

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail

class EditingBookDetail(
        private val book: Book
) {

    fun doEdit(bookDetail: BookDetail): Book {
        return book.edit(bookDetail)
    }
}