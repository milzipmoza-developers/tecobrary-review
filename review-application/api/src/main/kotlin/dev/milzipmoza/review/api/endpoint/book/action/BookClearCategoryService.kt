package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.service.EditingBookCategory

@ApplicationService
class BookClearCategoryService(
        private val books: Books,
        private val bookOperation: BookOperation
) {

    fun clear(isbn: String): Boolean {

        val book = books.findBy(isbn)

        val command = EditingBookCategory(book)

        val categoryClearedBook = command.clear()

        return bookOperation.edit(categoryClearedBook)
    }
}
