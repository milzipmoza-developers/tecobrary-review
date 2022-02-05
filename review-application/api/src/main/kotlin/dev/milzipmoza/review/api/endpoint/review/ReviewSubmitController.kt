package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ClientMember
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewSubmitController(
        private val reviewSubmitService: ReviewSubmitNotUseDraftService
) {

    @PostMapping("/api/reviews/submit")
    fun submit(@RequestAttribute(ClientMember.ATTRIBUTE_NAME) clientMember: ClientMember,
               @RequestBody body: ReviewSubmitDto): ApiResponse<Boolean> {
        val result = reviewSubmitService.submit(clientMember, body)
        return ApiResponse.success(message = "리뷰가 등록되었어요", data = result)
    }
}
