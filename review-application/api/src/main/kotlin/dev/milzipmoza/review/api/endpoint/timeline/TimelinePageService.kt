package dev.milzipmoza.review.api.endpoint.timeline

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.member.Members
import dev.milzipmoza.review.domain.review.Reviews

@ApplicationService
class TimelinePageService(
        private val reviews: Reviews,
        private val members: Members,
        private val books: Books
) {

    public fun getMore(size: Int, lastReviewNo: String?): List<TimelineContentDto> {
        val recentReviews = reviews.getRecent(size, lastReviewNo)

        val reviewMemberNos = recentReviews.map { it.member.no }
        val reviewMembers = members.findAllIn(reviewMemberNos)

        val reviewBookIsbns = recentReviews.map { it.book.isbn }
        val reviewBooks = books.findAllIn(reviewBookIsbns)

        return recentReviews.map { review ->
            val member = reviewMembers.find { review.member.no == it.no }!!
            val book = reviewBooks.find { review.book.isbn == it.isbn }!!
            TimelineContentMapper.map(member, book, review)
        }.toList()
    }
}