package dev.milzipmoza.review.api.endpoint.bookmarks

import java.time.LocalDateTime

data class RecentBookmarkRequest(
        val lastItemMarkDateTime: String? = null,
        val size: Long = 10
)