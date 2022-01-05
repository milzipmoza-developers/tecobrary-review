package dev.milzipmoza.review.domain.book.service

import dev.milzipmoza.review.domain.book.BookOperationException
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.tag.BookTag

class EditingBookTag(
        private val book: Book
) {

    fun add(bookTags: Set<BookTag>): Book {
        if (book.tags.contains(bookTags)) {
            throw BookOperationException("이미 태그가 추가된 태그가 있습니다.")
        }
        return book.add(*bookTags.toTypedArray())
    }

    fun remove(bookTag: BookTag): Book {
        return book.remove(bookTag)
    }
}