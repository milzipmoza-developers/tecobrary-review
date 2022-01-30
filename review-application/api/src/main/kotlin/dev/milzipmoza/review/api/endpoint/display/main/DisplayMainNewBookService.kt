package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.endpoint.display.main.dto.DisplayMainNewBookDto
import dev.milzipmoza.review.api.endpoint.display.main.dto.DisplayMainNewBookSectionDto
import dev.milzipmoza.review.domain.book.Books
import java.time.LocalDate

@ApplicationService
class DisplayMainNewBookService(
        private val books: Books
) {

    fun getRecentPublished(): DisplayMainNewBookSectionDto {
        val newBooks = books.getRecentPublished(12L, 10)
        return DisplayMainNewBookSectionDto(
                updateDate = LocalDate.now(),
                books = newBooks.map { DisplayMainNewBookDto.of(it) }
                        .toList()
        )
    }
}