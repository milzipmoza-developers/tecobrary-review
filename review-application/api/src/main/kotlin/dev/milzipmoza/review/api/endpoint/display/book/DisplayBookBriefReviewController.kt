package dev.milzipmoza.review.api.endpoint.display.book

import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DisplayBookBriefReviewController(
        private val displayBookBriefReviewService: DisplayBookBriefReviewService
) {

    @GetMapping("/api/displays/books/{isbn}/reviews")
    fun getBrief(@PathVariable isbn: String,
                   @RequestParam range: String): ApiResponse<DisplayBookBriefReviewDto> {
        val brief = displayBookBriefReviewService.brief(isbn, range)
        return ApiResponse.success(data = brief)
    }
}