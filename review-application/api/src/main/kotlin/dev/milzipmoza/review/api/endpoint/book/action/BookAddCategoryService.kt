package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.BookOperationException
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.service.EditingBookCategory
import dev.milzipmoza.review.domain.category.Categories

@ApplicationService
class BookAddCategoryService(
        private val categories: Categories,
        private val books: Books,
        private val bookOperation: BookOperation,
) {
    fun add(isbn: String, categoryDto: BookAddCategoryDto): Boolean {
        if (categories.isNotExistBy(categoryDto.no)) {
            throw BookOperationException("카테고리를 확인해주세요.")
        }

        val book = books.findBy(isbn)

        val command = EditingBookCategory(book)

        val category = BookAddCategoryDtoMapper.map(categoryDto)

        val categoryAddedBook = command.fill(category)

        return bookOperation.edit(categoryAddedBook)
    }
}
