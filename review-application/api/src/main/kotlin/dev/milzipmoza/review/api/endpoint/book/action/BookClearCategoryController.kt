package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.api.AdminMemberDto
import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class BookClearCategoryController(
        private val bookClearCategoryService: BookClearCategoryService
) {

    @PostMapping("/api/books/{isbn}/clear-category")
    fun clearCategory(@PathVariable isbn: String,
                      @RequestAttribute(AdminMemberDto.ATTRIBUTE_NAME) adminMemberDto: AdminMemberDto): ApiResponse<Boolean> {
        val result = bookClearCategoryService.clear(isbn)
        return ApiResponse.success(
                message = "도서 카테고리를 해제하였습니다.",
                data = result
        )
    }
}