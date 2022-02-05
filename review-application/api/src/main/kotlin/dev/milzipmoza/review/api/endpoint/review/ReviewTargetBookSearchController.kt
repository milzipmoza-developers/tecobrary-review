package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewTargetBookSearchController(
        private val reviewTargetBookSearchService: ReviewTargetBookSearchService
) {

    @GetMapping("/api/reviews/search-books")
    fun search(@RequestParam("keyword") keyword: String): ApiResponse<PageData<ReviewTargetSearchBookDto>> {
        val pageData = reviewTargetBookSearchService.search(keyword)
        return ApiResponse.success(data = pageData)
    }
}