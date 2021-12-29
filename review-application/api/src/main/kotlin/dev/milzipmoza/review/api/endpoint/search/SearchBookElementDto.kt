package dev.milzipmoza.review.api.endpoint.search

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.domain.search.model.SearchBook
import java.time.LocalDate

data class SearchBookElementDto(
        val isbn: String,
        val title: String,
        val publisher: String,
        val author: String,
        val imageUrl: String,
        val description: String,
        @JsonFormat(pattern = "yyyy-MM-dd")
        val publishDate: LocalDate
) {
    companion object {
        fun of(searchBook: SearchBook): SearchBookElementDto {
            return SearchBookElementDto(
                    isbn = searchBook.isbn,
                    title = searchBook.title,
                    publisher = searchBook.publisher,
                    author = searchBook.author,
                    imageUrl = searchBook.imageUrl,
                    description = searchBook.descriptionContent,
                    publishDate = searchBook.publishDate
            )
        }
    }
}