package dev.milzipmoza.review.api.endpoint.timeline

import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TimelineMorePageController(
        private val timelinePageService: TimelinePageService
) {

    @GetMapping("/api/timeline")
    fun getMore(morePageRequest: TimelineMorePageRequest): ApiResponse<List<TimelineContentDto>> {
        val timelineContents = timelinePageService.getMore(morePageRequest.size, morePageRequest.lastAttribute)

        return ApiResponse.success(data = timelineContents)
    }
}