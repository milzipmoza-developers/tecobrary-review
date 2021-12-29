package dev.milzipmoza.review.api.endpoint.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchBookController(
        private val searchBookService: SearchBookService
) {

    @GetMapping("/api/search-books")
    fun search(searchRequest: SearchBookPageRequest): ApiResponse<PageData<SearchBookElementDto>> {
        val pageData = searchBookService.search(searchRequest.keyword, searchRequest.page, searchRequest.size)
        return ApiResponse.success(data = pageData)
    }
}