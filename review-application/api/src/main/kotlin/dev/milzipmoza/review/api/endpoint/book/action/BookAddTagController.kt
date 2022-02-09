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
class BookAddTagController(
        private val bookAddTagService: BookAddTagService
) {

    @PostMapping("/api/books/{isbn}/add-tag")
    fun addTag(@PathVariable isbn: String,
               @RequestBody body: ApiUpdateBody<List<BookAddTagDto>>,
               @RequestAttribute(AdminMemberDto.ATTRIBUTE_NAME) adminMemberDto: AdminMemberDto): ApiResponse<Boolean> {
        val result = bookAddTagService.add(isbn, body.update)
        return ApiResponse.success(
                message = "도서에 태그를 추가하였습니다.",
                data = result
        )
    }
}