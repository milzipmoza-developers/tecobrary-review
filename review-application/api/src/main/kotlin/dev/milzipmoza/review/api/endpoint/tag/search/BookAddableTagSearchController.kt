package dev.milzipmoza.review.api.endpoint.tag.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookAddableTagSearchController(
        private val bookAddableTagSearchService: BookAddableTagSearchService
) {

    @GetMapping("/api/tags/book-addable")
    fun get(pageParam: PageRequest, tagSearchRequest: TagSearchRequest, bookAddableRequest: BookAddableRequest): ApiResponse<PageData<TagDto>> {
        val tags = bookAddableTagSearchService.search(pageParam.page, pageParam.size, tagSearchRequest.tagName, bookAddableRequest.isbn)
        return ApiResponse.success(data = tags)
    }
}