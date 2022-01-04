package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.model.tag.BookTag
import dev.milzipmoza.review.domain.book.service.EditingBookTag

@ApplicationService
class BookRemoveTagService(
        private val books: Books,
        private val bookOperation: BookOperation
) {

    fun remove(isbn: String, update: BookRemoveTagDto): Boolean {

        val book = books.findBy(isbn)

        val command = EditingBookTag(book)

        val tagRemovedBook = command.remove(BookTag(update.no, update.name, update.colorCode))

        return bookOperation.edit(tagRemovedBook)
    }
}
