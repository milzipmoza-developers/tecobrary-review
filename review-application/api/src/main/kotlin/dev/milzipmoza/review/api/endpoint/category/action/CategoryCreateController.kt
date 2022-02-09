package dev.milzipmoza.review.api.endpoint.category.action

import dev.milzipmoza.review.api.*
import org.springframework.web.bind.annotation.*

@RestController
class CategoryCreateController(
        private val categoryCreateService: CategoryCreateService
) {
    @PostMapping("/api/categories")
    fun createCategory(@RequestBody body: ApiCreateBody<CreateCategoryDto>,
                       @RequestAttribute(AdminMemberDto.ATTRIBUTE_NAME) adminMemberDto: AdminMemberDto): ApiResponse<String> {
        val categoryNo = categoryCreateService.doCreate(body.create)
        return ApiResponse.success(data = categoryNo)
    }
}