package dev.milzipmoza.review.api.endpoint.tag.action.addbook

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.tag.service.AddingTagBook
import dev.milzipmoza.review.domain.tag.TagOperation
import dev.milzipmoza.review.domain.tag.Tags

@ApplicationService
class TagAddBookService(
        private val books: Books,
        private val tags: Tags,
        private val tagOperation: TagOperation
) {

    fun add(tagNo: String, isbn: List<String>): Boolean {
        if (isbn.isEmpty()) {
            throw IllegalArgumentException("추가할 도서를 확인해주세요.")
        }
        if (isbn.size != 1) {
            throw IllegalArgumentException("준비중인 기능입니다.")
        }

        val book = books.findBy(isbn[0])

        val command = AddingTagBook(tags.findBy(tagNo))
        val tag = command.doAdd(book)

        return tagOperation.update(tag)
    }
}