package dev.milzipmoza.review.api.endpoint.category

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.api.PageRequest
import dev.milzipmoza.review.domain.category.model.Category
import dev.milzipmoza.review.mongo.category.domain.MongoCategories
import dev.milzipmoza.review.mongo.category.domain.MongoCategoryOperation
import org.springframework.web.bind.annotation.*

@RestController
class CategoryController(
        private val mongoCategoryOperation: MongoCategoryOperation
) {
    @PostMapping("/api/categories")
    fun createCategory(@RequestBody body: CreateCategoryRequest): ApiResponse<String> {
        val category = body.toModel()
        val categoryNo = mongoCategoryOperation.save(category)
        return ApiResponse.success(data = categoryNo)
    }
}