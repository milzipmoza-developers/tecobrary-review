package dev.milzipmoza.review.api.endpoint.timeline

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.domain.review.Reviews
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TimelineHasMoreController(
        private val reviews: Reviews
) {

    @GetMapping("/api/timeline/has-more")
    fun getHasMore(latestReviewNo: String): ApiResponse<Boolean> {
        val latestReview = reviews.getLatest()
        val hasMore = latestReviewNo == (latestReview?.no ?: false)
        return ApiResponse.success(data = hasMore)
    }
}