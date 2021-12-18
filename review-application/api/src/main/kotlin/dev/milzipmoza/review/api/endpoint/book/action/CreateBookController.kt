package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.api.ApiCreateBody
import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateBookController(
        private val createBookService: CreateBookService
) {

    @PostMapping("/api/books")
    fun create(@RequestBody body: ApiCreateBody<CreateBookDto>): ApiResponse<Boolean> {
        val result = createBookService.create(body.create)
        return ApiResponse.success(data = result)
    }
}