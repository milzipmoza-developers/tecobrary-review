package dev.milzipmoza.review.api.endpoint.book.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.domain.book.Books
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(
        private val books: Books
) {

    @GetMapping("/api/books/{isbn}")
    fun findBy(@PathVariable isbn: String): ApiResponse<BookDto> {
        val book = books.findBy(isbn)
        return ApiResponse.success(data = BookDtoMapper.map(book))
    }
}