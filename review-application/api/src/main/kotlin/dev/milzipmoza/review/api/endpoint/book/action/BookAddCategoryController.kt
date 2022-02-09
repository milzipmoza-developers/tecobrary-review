package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.api.AdminMemberDto
import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ApiUpdateBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookAddCategoryController(
        private val bookAddCategoryService: BookAddCategoryService
) {

    @PostMapping("/api/books/{isbn}/add-category")
    fun addCategory(@PathVariable isbn: String,
                    @RequestBody body: ApiUpdateBody<BookAddCategoryDto>,
                    @RequestAttribute(AdminMemberDto.ATTRIBUTE_NAME) adminMemberDto: AdminMemberDto): ApiResponse<Boolean> {
        val result = bookAddCategoryService.add(isbn, body.update)
        return ApiResponse.success(
                message = "도서에 카테고리를 추가하였습니다.",
                data = result
        )
    }
}