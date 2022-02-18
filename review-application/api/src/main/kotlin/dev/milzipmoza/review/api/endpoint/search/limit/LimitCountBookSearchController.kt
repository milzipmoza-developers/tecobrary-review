package dev.milzipmoza.review.api.endpoint.search.limit

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LimitCountBookSearchController(
        private val limitCountBookSearchService: LimitCountBookSearchService
) {

    @GetMapping(
            "/api/reviews/search-books",
            "/api/search-books/limited"
    )
    fun search(@RequestParam("keyword") keyword: String): ApiResponse<PageData<LimitCountSearchBookDto>> {
        val pageData = limitCountBookSearchService.search(keyword)
        return ApiResponse.success(data = pageData)
    }
}