package dev.milzipmoza.review.api.endpoint.bookmarks

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.ClientMember
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.mark.Bookmark
import dev.milzipmoza.review.domain.mark.Bookmarks
import dev.milzipmoza.review.domain.mark.MarkMember
import java.time.LocalDateTime


@ApplicationService
class RecentBookmarkService(
        private val bookmarks: Bookmarks,
        private val books: Books
) {
    fun getRecent(clientMember: ClientMember, size: Long, lastItemMarkDateTime: String?): List<RecentBookmarkDto> {
        return when (clientMember) {
            is ClientMember.AuthenticatedMember -> {
                val dateTime = lastItemMarkDateTime?.let { LocalDateTime.parse(it) }
                val bookmarks: List<Bookmark> = this.bookmarks.getRecentBookmarkAfter(MarkMember(clientMember.memberNo), size, dateTime)
                val bookIsbns = bookmarks.map { it.book.no }
                val books = books.findAllIn(bookIsbns)
                bookmarks.map { bookmark ->
                    val book = books.find { it.isbn == bookmark.book.no }!!
                    RecentBookmarkDto(
                            isbn = book.isbn,
                            title = book.detail.summarizedTitle,
                            imageUrl = book.detail.fullImageUrl,
                            markDateTime = bookmark.markDateTime,
                            tags = book.tags.map { RecentBookmarkTagDto(it.name, it.colorCode) }
                    )
                }
            }
            else -> listOf()
        }
    }
}