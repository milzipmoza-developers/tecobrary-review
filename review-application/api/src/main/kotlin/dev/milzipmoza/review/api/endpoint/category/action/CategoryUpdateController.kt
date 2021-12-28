package dev.milzipmoza.review.api.endpoint.category.action

import dev.milzipmoza.review.api.*
import org.springframework.web.bind.annotation.*

@RestController
class CategoryUpdateController(
        private val categoryUpdateService: CategoryUpdateService,
) {

    @PostMapping("/api/categories/{categoryNo}")
    fun updateCategory(@PathVariable categoryNo: String,
                       @RequestBody body: ApiUpdateBody<UpdateCategoryDto>): ApiResponse<String> {
        val updatedCategoryNo = categoryUpdateService.doUpdate(categoryNo, body.update)
        return ApiResponse.success(data = updatedCategoryNo)
    }
}