package dev.milzipmoza.review.api.endpoint.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.search.SearchBooks
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchBookController(
        private val searchBooks: SearchBooks
) {

    @GetMapping("/api/search-books")
    fun search(pageParam: SearchBookPageRequest): ApiResponse<PageData<SearchBookElementDto>> {
        val pageSearchResult = searchBooks.findAllBy(pageParam.keyword, PageQuery(pageParam.page, pageParam.size))

        return ApiResponse.success(data = PageData(
                total = pageSearchResult.total,
                size = pageSearchResult.size,
                isFirst = pageSearchResult.isFirst,
                isLast = pageSearchResult.isLast,
                items = pageSearchResult.items
                        .map { SearchBookElementDto.of(it) }
                        .toList()
        ))
    }
}