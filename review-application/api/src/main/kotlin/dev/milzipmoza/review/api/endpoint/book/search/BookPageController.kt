package dev.milzipmoza.review.api.endpoint.book.search

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookPageController(
        private val bookPageService: BookPageService
) {

    @GetMapping("/api/books")
    fun getBooks(pageRequest: PageRequest): ApiResponse<PageData<BookDto>> {
        val pageData = bookPageService.findAll(pageRequest.page, pageRequest.size)
        return ApiResponse.success(data = pageData)
    }
}