package dev.milzipmoza.review.domain.tag.service

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.tag.TagOperationException
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.book.TagBook

class AddingTagBook(
        private val tag: Tag
) {

    fun doAdd(book: Book): Tag {
        val tagBook = TagBook(book.isbn)

        if (tag.books.contains(tagBook)) {
            throw TagOperationException("태그에 도서가 이미 추가되어 있습니다.")
        }

        return tag.add(tagBook)
    }
}