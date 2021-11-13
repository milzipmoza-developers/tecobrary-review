package dev.milzipmoza.domain.book.model.review

import dev.milzipmoza.domain.book.model.reviewer.BookReviewer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BookReviewTest {

    @Test
    fun `식별자와 type 이 같으면 같은 리뷰로 간주한다`() {
        val sameReviewNo = "123"

        val it = BookReview.CommentBookReview(
                no = sameReviewNo,
                reviewer = BookReviewer(
                        no = "456",
                        name = "루피"
                ),
                score = BookReviewScore.BAD,
                range = BookReviewRange.A_LITTLE,
                content = "좋아요"
        )

        val other = BookReview.CommentBookReview(
                no = sameReviewNo,
                reviewer = BookReviewer(
                        no = "789",
                        name = "상디"
                ),
                score = BookReviewScore.BEST,
                range = BookReviewRange.READ_ALL,
                content = "싫어요"
        )

        val sameAs = it.sameAs(other)

        assertTrue(sameAs)
    }

    @Test
    fun `type 이 다르면 다른 리뷰로 간주한다`() {
        val sameReviewNo = "123"

        val it = BookReview.CommentBookReview(
                no = sameReviewNo,
                reviewer = BookReviewer(
                        no = "456",
                        name = "루피"
                ),
                score = BookReviewScore.BAD,
                range = BookReviewRange.A_LITTLE,
                content = "좋아요"
        )

        val other = BookReview.BlogBookReview(
                no = sameReviewNo,
                reviewer = BookReviewer(
                        no = "789",
                        name = "상디"
                ),
                score = BookReviewScore.BEST,
                range = BookReviewRange.READ_ALL,
                fullUrl = "https://url/tistory",
                briefContent = "이것은 리뷰인가",
        )

        val sameAs = it.sameAs(other)

        assertFalse(sameAs)
    }
}