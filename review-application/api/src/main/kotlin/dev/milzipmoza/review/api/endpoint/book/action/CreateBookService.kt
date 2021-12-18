package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.BookOperation

@ApplicationService
class CreateBookService(
        private val bookOperation: BookOperation
) {

    fun create(createBookDto: CreateBookDto): Boolean {
        val book = CreateBookDtoMapper.map(createBookDto)
        return bookOperation.save(book)
    }
}