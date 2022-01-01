package dev.milzipmoza.review.api.endpoint.search

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.search.SearchBooks

@ApplicationService
class SearchBookService(
        private val searchBooks: SearchBooks
) {

    fun search(keyword: String?, page: Int, size: Int): PageData<SearchBookElementDto> {
        if (keyword.isNullOrBlank() || keyword.length < 2) {
            throw IllegalArgumentException("검색어를 두 글자 이상 입력해주세요.")
        }
        if (page < 1) {
            throw IllegalArgumentException("페이지는 1부터 시작하는 값 입니다.")
        }
        val pageEntities = searchBooks.findAllBy(keyword, PageQuery(page, size))
        return PageData.of(pageEntities, SearchBookElementDto::of)
    }
}