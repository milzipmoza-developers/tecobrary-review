package dev.milzipmoza.review.api.endpoint.bookmarks

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ClientMember
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class RecentBookmarkController(
        private val recentBookmarkService: RecentBookmarkService
) {

    @GetMapping("/api/bookmarks")
    fun get(@RequestAttribute(ClientMember.ATTRIBUTE_NAME) clientMember: ClientMember,
            request: RecentBookmarkRequest): ApiResponse<List<RecentBookmarkDto>> {
        val bookmarks = recentBookmarkService.getRecent(clientMember, request.size, request.lastItemMarkDateTime)
        return ApiResponse.success(data = bookmarks)
    }
}