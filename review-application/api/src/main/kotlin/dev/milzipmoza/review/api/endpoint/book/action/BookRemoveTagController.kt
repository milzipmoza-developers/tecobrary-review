package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ApiUpdateBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookRemoveTagController(
        private val bookRemoveTagService: BookRemoveTagService
) {

    @PostMapping("/api/books/{isbn}/remove-tag")
    fun addTag(@PathVariable isbn: String,
               @RequestBody body: ApiUpdateBody<BookRemoveTagDto>): ApiResponse<Boolean> {
        val result = bookRemoveTagService.remove(isbn, body.update)
        return ApiResponse.success(
                message = "도서에 태그를 추가하였습니다.",
                data = result
        )
    }
}