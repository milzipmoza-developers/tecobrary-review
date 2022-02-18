package dev.milzipmoza.review.api.endpoint.review.selectbook

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ClientMember
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SelectReviewBookController(
        private val selectReviewBookService: SelectReviewBookService
) {

    @PostMapping("/api/reviews/select-book")
    fun select(@RequestAttribute(ClientMember.ATTRIBUTE_NAME) clientMember: ClientMember,
               @RequestBody selectReviewBook: SelectReviewBookDto): ApiResponse<SelectReviewBookResultDto> {
        val selectResult = selectReviewBookService.select(clientMember, selectReviewBook)
        return ApiResponse.success(data = selectResult)
    }
}