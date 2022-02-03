package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books
import java.time.LocalDate

@ApplicationService
class DisplayMainNewBookService(
        private val books: Books
) {

    fun getRecentPublished(): DisplayMainNewBookSectionDto {
        val newBooks = books.getRecentPublished(12L, PageQuery(0, 10)).items
        return DisplayMainNewBookSectionDto(
                updateDate = LocalDate.now(),
                books = newBooks.map { DisplayMainNewBookDto.of(it) }
                        .toList()
        )
    }
}