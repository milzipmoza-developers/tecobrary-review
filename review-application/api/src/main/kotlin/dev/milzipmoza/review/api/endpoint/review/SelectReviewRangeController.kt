package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ClientMember
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SelectReviewRangeController(
        private val selectReviewRangeService: SelectReviewRangeService
) {

    @PostMapping("/api/reviews/select-ranges")
    fun select(@RequestAttribute(ClientMember.ATTRIBUTE_NAME) clientMember: ClientMember,
               @RequestBody body: SelectReviewRangeDto): ApiResponse<SelectReviewRangeResultDto> {
        val data = selectReviewRangeService.select(clientMember, body)
        return ApiResponse.success(data = data)
    }
}
