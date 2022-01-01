package dev.milzipmoza.review.api.endpoint.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.KeywordRequest
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchBookController(
        private val searchBookService: SearchBookService
) {

    @GetMapping("/api/search-books")
    fun search(pageRequest: PageRequest,
               keywordRequest: KeywordRequest): ApiResponse<PageData<SearchBookElementDto>> {
        val pageData = searchBookService.search(keywordRequest.keyword, pageRequest.page, pageRequest.size)
        return ApiResponse.success(data = pageData)
    }
}