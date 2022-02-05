package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ClientMember
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewSelectableRangeController(
        private val reviewSelectableRangeService: ReviewSelectableRangeService
) {

    @GetMapping("/api/review/available-ranges")
    fun get(@RequestAttribute(ClientMember.ATTRIBUTE_NAME) clientMember: ClientMember,
            @RequestParam isbn: String): ApiResponse<ReviewSelectableRangeDto> {
        val data = reviewSelectableRangeService.getAvailableRanges(clientMember, isbn)
        return ApiResponse.success(data = data)
    }
}