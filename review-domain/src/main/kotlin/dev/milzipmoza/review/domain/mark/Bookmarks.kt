package dev.milzipmoza.review.domain.mark

import java.time.LocalDateTime

interface Bookmarks {

    fun getRecentBookmarkAfter(markMember: MarkMember, size: Long, before: LocalDateTime? = null): List<Bookmark>
}