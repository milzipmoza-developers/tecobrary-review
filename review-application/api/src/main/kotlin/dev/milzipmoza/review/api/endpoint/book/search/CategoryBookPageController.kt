package dev.milzipmoza.review.api.endpoint.book.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryBookPageController(
        private val categoryBookPageService: CategoryBookPageService
) {

    @GetMapping("/api/books/by-categories")
    fun getBooksByCategoryNo(pageRequest: PageRequest, @RequestParam categoryNo: String): ApiResponse<CategoryBookPageDto> {
        val pageData = categoryBookPageService.findAll(pageRequest.page, pageRequest.size, categoryNo)
        return ApiResponse.success(data = pageData)
    }
}