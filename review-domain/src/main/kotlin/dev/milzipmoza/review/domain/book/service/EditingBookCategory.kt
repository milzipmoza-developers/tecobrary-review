package dev.milzipmoza.review.domain.book.service

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory

class EditingBookCategory(
        private val book: Book
) {

    fun fill(bookCategory: BookCategory.EnrolledBookCategory): Book {
        return book.edit(bookCategory)
    }

    fun clear(): Book {
        return book.edit(BookCategory.hasNoCategory())
    }
}