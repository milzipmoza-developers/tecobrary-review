package dev.milzipmoza.review.api.endpoint.book.search

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books

@ApplicationService
class BookPageService(
        private val books: Books
) {

    fun findAll(page: Int, size: Int): PageData<BookDto> {
        val pageQuery = PageQuery(page, size)
        return books.findBy(pageQuery)
                .run { PageData.of(this, BookDtoMapper::map) }
    }
}