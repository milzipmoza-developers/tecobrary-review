package dev.milzipmoza.review.api.endpoint.member

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class MemberMyInfoDto(
        val member: MemberDto,
        val bookmarks: List<BookmarkDto>
)

data class MemberDto(
        val name: String,
        val email: String,
        val profileImageUrl: String,
        val blogUrl: String,
        val description: String
)

data class BookmarkDto(
        val isbn: String,
        val title: String,
        val imageUrl: String,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        val markDateTime: LocalDateTime,
        val tags: List<BookmarkTagDto>
)

data class BookmarkTagDto(
        val name: String,
        val colorCode: String
)
