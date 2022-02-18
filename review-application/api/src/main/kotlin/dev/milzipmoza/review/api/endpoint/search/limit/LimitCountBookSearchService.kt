package dev.milzipmoza.review.api.endpoint.search.limit

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.search.SearchBooks

@ApplicationService
class LimitCountBookSearchService(
        private val searchBooks: SearchBooks,
        private val books: Books,
        private val searchResultProcessService: SearchResultProcessService
) {

    fun search(keyword: String): PageData<LimitCountSearchBookDto> {
        if (keyword.length < 2) {
            throw IllegalArgumentException("두 글자 이상부터 검색이 가능해요")
        }
        val foundSearchBooks = searchBooks.findAllBy(keyword, PageQuery(1, 5))
        searchResultProcessService.process(foundSearchBooks.items)
        val isbns = foundSearchBooks.items.map { it.isbn }
        val foundBooks = books.findAllIn(isbns)
        return PageData.of(foundSearchBooks) {
            LimitCountSearchBookDto(
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