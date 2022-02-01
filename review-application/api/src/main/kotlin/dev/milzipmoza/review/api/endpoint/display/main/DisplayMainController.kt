package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DisplayMainController(
        private val displayMainNewBookService: DisplayMainNewBookService,
        private val displayMainInterestBookService: DisplayMainInterestBookService,
        private val displayMainCategoryService: DisplayMainCategoryService
) {

    @GetMapping("/api/display/main/new-books")
    fun getNewBooks(): ApiResponse<DisplayMainNewBookSectionDto> {
        val displayNewBooks = displayMainNewBookService.getRecentPublished()
        return ApiResponse.success(data = displayNewBooks)
    }

    @GetMapping("/api/display/main/interest-books")
    fun getInterestBooks(type: String): ApiResponse<DisplayMainInterestBookSectionDto> {
        val displayInterestBookSection = displayMainInterestBookService.getByType(type)
        return ApiResponse.success(data = displayInterestBookSection)
    }

    @GetMapping("/api/display/main/categories")
    fun getCategories(): ApiResponse<List<DisplayMainCategoryDto>> {
        val displayRandomCategories = displayMainCategoryService.getRandomCategories()
        return ApiResponse.success(data = displayRandomCategories)
    }
}