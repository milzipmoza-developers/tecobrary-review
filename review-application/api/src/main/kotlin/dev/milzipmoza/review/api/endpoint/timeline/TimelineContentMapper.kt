package dev.milzipmoza.review.api.endpoint.timeline

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.member.model.Member
import dev.milzipmoza.review.domain.review.model.Review

object TimelineContentMapper {

    fun map(member: Member, book: Book, review: Review) =
            TimelineContentDto(
                    member = map(member),
                    book = map(book),
                    review = map(review)
            )

    private fun map(member: Member) = TimelineContentMemberDto(
            no = member.no,
            name = member.info.name,
            profileImageUrl = member.info.profileImageUrl
    )

    private fun map(book: Book) = TimelineContentBookDto(
            isbn = book.isbn,
            title = book.detail.summarizedTitle,
            imageUrl = book.detail.fullImageUrl,
            tags = book.tags.map { TimelineContentTagDto(it.name, it.colorCode) }
    )

    private fun map(review: Review) = TimelineContentReviewDto(
            no = review.no,
            range = review.range.name,
            content = review.keyword.content.name,
            informative = review.keyword.informative.name,
            readMore = review.keyword.readMore?.name,
            selectables = review.keyword.selectables.map { it.name }
    )
}