package dev.milzipmoza.review.api.endpoint.search

import dev.milzipmoza.review.domain.search.model.SearchBook

data class SearchBookElementDto(
        val isbn: String,
        val title: String,
        val publisher: String,
        val author: String,
        val imageUrl: String
) {
    companion object {
        fun of(searchBook: SearchBook): SearchBookElementDto {
            return SearchBookElementDto(
                    isbn = searchBook.isbn,
                    title = searchBook.title,
                    publisher = searchBook.publisher,
                    author = searchBook.author,
                    imageUrl = searchBook.imageUrl
            )
        }
    }
}