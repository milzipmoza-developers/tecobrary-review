package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.BookOperationException
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.model.tag.BookTag
import dev.milzipmoza.review.domain.book.service.EditingBookTag
import dev.milzipmoza.review.domain.tag.Tags

@ApplicationService
class BookAddTagService(
        private val tags: Tags,
        private val books: Books,
        private val bookOperation: BookOperation
) {

    fun add(isbn: String, update: List<BookAddTagDto>): Boolean {

        val book = books.findBy(isbn)

        val foundTags = tags.findAllBy(update.map { it.no })

        val command = EditingBookTag(book)

        val tagAddedBook = command.add(foundTags.map { BookTag(it.no, it.name.name, it.color.code) })

        return bookOperation.edit(tagAddedBook)
    }
}
