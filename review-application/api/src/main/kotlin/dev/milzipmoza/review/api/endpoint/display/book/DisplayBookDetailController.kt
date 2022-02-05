package dev.milzipmoza.review.api.endpoint.display.book

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ClientMember
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class DisplayBookDetailController(
        private val displayBookDetailService: DisplayBookDetailService
) {

    @GetMapping("/api/displays/books/{isbn}")
    fun get(@PathVariable isbn: String,
            @RequestAttribute(ClientMember.ATTRIBUTE_NAME) clientMember: ClientMember): ApiResponse<DisplayBookDto> {
        val data = displayBookDetailService.get(isbn, clientMember)
        return ApiResponse.success(data = data)
    }
}