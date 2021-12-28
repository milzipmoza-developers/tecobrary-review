package dev.milzipmoza.review.api.endpoint.category.search

import dev.milzipmoza.review.api.*
import org.springframework.web.bind.annotation.*

@RestController
class CategoryController(
        private val categoryQueryService: CategoryQueryService
) {

    @GetMapping("/api/categories/{categoryNo}")
    fun getCategory(@PathVariable categoryNo: String): ApiResponse<CategoryDto> {
        val category = categoryQueryService.getCategory(categoryNo)
        return ApiResponse.success(data = category)
    }

    @GetMapping("/api/categories")
    fun categories(pageParam: PageRequest): ApiResponse<PageData<CategoryDto>> {
        val categories = categoryQueryService.getCategories(pageParam.page, pageParam.size)
        return ApiResponse.success(data = categories)
    }
}