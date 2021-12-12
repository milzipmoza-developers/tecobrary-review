package dev.milzipmoza.review.api.endpoint.marks.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AllMarkSearchController(
        private val allMarkSearchService: AllMarkSearchService
) {

    @GetMapping("/api/marks")
    private fun get(pageParam: PageRequest, markSearchRequest: MarkSearchRequest): ApiResponse<PageData<MarkDto>> {
        val marks = allMarkSearchService.search(pageParam.page, pageParam.size, markSearchRequest.bookNo, markSearchRequest.memberNo)
        return ApiResponse.success(data = marks)
    }
}