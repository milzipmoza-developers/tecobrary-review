package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.EditingBookDetail


@ApplicationService
class UpdateBookService(
        private val books: Books,
        private val bookOperation: BookOperation
) {

    fun update(isbn: String, update: UpdateBookDto): Boolean {
        val book = books.findBy(isbn)

        val command = EditingBookDetail(book)

        val bookDetail = UpdateBookDtoMapper.map(update, book)

        val editedBook = command.doEdit(bookDetail)

        return bookOperation.edit(editedBook)
    }
}