package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ApiUpdateBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateBookController(
        private val updateBookService: UpdateBookService
) {

    @PostMapping("/api/books/{isbn}")
    fun edit(@PathVariable isbn: String,
             @RequestBody body: ApiUpdateBody<UpdateBookDto>): ApiResponse<Boolean> {
        val result = updateBookService.update(isbn, body.update)
        return ApiResponse.success(data = result)
    }
}
