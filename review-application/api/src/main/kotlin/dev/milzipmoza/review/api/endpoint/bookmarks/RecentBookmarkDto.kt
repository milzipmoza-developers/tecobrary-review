package dev.milzipmoza.review.api.endpoint.bookmarks

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.api.endpoint.member.BookmarkTagDto
import java.time.LocalDateTime

data class RecentBookmarkDto(
        val isbn: String,
        val title: String,
        val imageUrl: String,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        val markDateTime: LocalDateTime,
        val tags: List<RecentBookmarkTagDto>
)

data class RecentBookmarkTagDto(
        val name: String,
        val colorCode: String
)
