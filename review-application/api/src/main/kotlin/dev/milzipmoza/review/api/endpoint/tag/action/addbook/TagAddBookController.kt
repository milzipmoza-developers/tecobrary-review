package dev.milzipmoza.review.api.endpoint.tag.action.addbook

import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TagAddBookController(
        private val tagAddBookService: TagAddBookService
) {

    @PostMapping("/api/tags/{tagNo}/add-books")
    fun addBook(@PathVariable tagNo: String,
                @RequestBody body: TagAddBookDto): ApiResponse<Boolean> {
        val result = tagAddBookService.add(tagNo, body.books)
        return ApiResponse.success(data = result)
    }
}
