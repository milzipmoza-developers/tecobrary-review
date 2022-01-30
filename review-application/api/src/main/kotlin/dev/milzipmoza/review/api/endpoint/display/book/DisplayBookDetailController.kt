package dev.milzipmoza.review.api.endpoint.display.book

import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DisplayBookDetailController(
        private val displayBookDetailService: DisplayBookDetailService
) {

    // TODO : memberNo 받을 수 있도록 수정
    @GetMapping("/api/displays/books/{isbn}")
    fun get(@PathVariable isbn: String): ApiResponse<DisplayBookDto> {
        val data = displayBookDetailService.get(isbn, null)
        return ApiResponse.success(data = data)
    }
}