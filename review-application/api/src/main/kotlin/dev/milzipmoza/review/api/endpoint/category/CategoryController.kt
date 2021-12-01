package dev.milzipmoza.review.api.endpoint.category

import dev.milzipmoza.review.api.*
import dev.milzipmoza.review.api.endpoint.category.service.CategoryCommandService
import dev.milzipmoza.review.api.endpoint.category.service.CategoryQueryService
import org.springframework.web.bind.annotation.*

@RestController
class CategoryController(
        private val categoryCommandService: CategoryCommandService,
        private val categoryQueryService: CategoryQueryService
) {
    @PostMapping("/api/categories")
    fun createCategory(@RequestBody request: ApiCreateRequest<CreateCategoryDto>): ApiResponse<String> {
        val categoryNo = categoryCommandService.createCategory(request.create)
        return ApiResponse.success(data = categoryNo)
    }

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