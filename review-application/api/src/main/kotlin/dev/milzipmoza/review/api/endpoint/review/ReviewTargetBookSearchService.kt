package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.search.SearchBooks

@ApplicationService
class ReviewTargetBookSearchService(
        private val searchBooks: SearchBooks,
        private val books: Books
) {

    fun search(keyword: String): PageData<ReviewTargetSearchBookDto> {
        if (keyword.length < 2) {
            throw IllegalArgumentException("두 글자 이상부터 검색이 가능해요")
        }
        val foundSearchBooks = searchBooks.findAllBy(keyword, PageQuery(1, 5))
        val isbns = foundSearchBooks.items.map { it.isbn }
        val foundBooks = books.findAllIn(isbns)
        return PageData.of(foundSearchBooks) {
            ReviewTargetSearchBookDto(
                    isbn = it.isbn,
                    title = it.title,
                    author = it.author,
                    publisher = it.publisher,
                    imageUrl = it.imageUrl,
                    description = it.description,
                    publishDate = it.publishDate,
                    tags = foundBooks.find { book -> book.isbn == it.isbn }
                            ?.tags
                            ?.map { tag -> ReviewSearchBookTag(tag.name, tag.colorCode) }
                            ?: emptyList()
            )
        }
    }
}