package dev.milzipmoza.review.domain.book.service

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.tag.BookTag

class EditingBookTag(
        private val book: Book
) {

    fun add(bookTags: List<BookTag>): Book {
        return book.add(*bookTags.toTypedArray())
    }

    fun remove(bookTag: BookTag): Book {
        return book.remove(bookTag)
    }
}