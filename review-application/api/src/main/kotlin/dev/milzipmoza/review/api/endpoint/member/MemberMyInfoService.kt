package dev.milzipmoza.review.api.endpoint.member

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.mark.*
import dev.milzipmoza.review.domain.member.Members

@ApplicationService
class MemberMyInfoService(
        private val members: Members,
        private val bookmarks: Bookmarks,
        private val books: Books
) {

    fun get(memberNo: String): MemberMyInfoDto {

        val member = members.findBy(memberNo)

        val bookmarks: List<Bookmark> = bookmarks.getRecentBookmarkAfter(MarkMember(member.no), 3)

        val bookIsbns = bookmarks.map { it.book.no }
        val books = books.findAllIn(bookIsbns)

        return MemberMyInfoDto(
                member = MemberDto(
                        name = member.info.name,
                        email = member.info.email,
                        profileImageUrl = member.info.profileImageUrl,
                        blogUrl = member.info.blogUrl,
                        description = member.info.description
                ),
                bookmarks = bookmarks.map { bookmark ->
                    val book = books.find { it.isbn == bookmark.book.no }!!
                    BookmarkDto(
                            isbn = book.isbn,
                            title = book.detail.summarizedTitle,
                            imageUrl = book.detail.fullImageUrl,
                            markDateTime = bookmark.markDateTime,
                            tags = book.tags.map { BookmarkTagDto(it.name, it.colorCode) }
                    )
                }
        )
    }
}