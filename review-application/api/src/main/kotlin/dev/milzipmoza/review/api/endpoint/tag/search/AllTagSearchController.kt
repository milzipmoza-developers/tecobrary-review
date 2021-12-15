package dev.milzipmoza.review.api.endpoint.tag.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AllTagSearchController(
        private val allTagSearchService: AllTagSearchService
) {

    @GetMapping("/api/tags")
    fun get(pageParam: PageRequest, tagSearchRequest: TagSearchRequest): ApiResponse<PageData<TagDto>> {
        val tags: PageData<TagDto> = allTagSearchService.search(pageParam.page, pageParam.size, tagSearchRequest.bookNo, tagSearchRequest.tagName)
        return ApiResponse.success(data = tags)
    }
}