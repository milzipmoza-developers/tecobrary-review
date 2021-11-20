package dev.milzipmoza.review.domain.book.model.review

import dev.milzipmoza.review.domain.book.model.reviewer.BookReviewer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BookReviewsTest {

    @Test
    fun `리뷰를 성공적으로 추가하면 true 를 반환한다`() {
        val reviews = BookReviews()

        val review = BookReview.CommentBookReview(
                no = "123",
                reviewer = BookReviewer(
                        no = "456",
                        name = "루피"
                ),
                score = BookReviewScore.BAD,
                range = BookReviewRange.A_LITTLE,
                content = "좋아요"

        )

        val result = reviews.add(review)

        assertTrue(result)
    }

    @Test
    fun `이미 있는 리뷰를 추가하면 예외가 발생한다`() {
        val sameReviewNo = "123"

        val reviews = BookReviews(mutableListOf(
                BookReview.CommentBookReview(
                        no = sameReviewNo,
                        reviewer = BookReviewer(
                                no = "456",
                                name = "루피"
                        ),
                        score = BookReviewScore.BAD,
                        range = BookReviewRange.A_LITTLE,
                        content = "좋아요"
                )
        ))

        val review = BookReview.CommentBookReview(
                no = sameReviewNo,
                reviewer = BookReviewer(
                        no = "456",
                        name = "루피"
                ),
                score = BookReviewScore.BAD,
                range = BookReviewRange.A_LITTLE,
                content = "좋아요"
        )

        assertThrows<BookReviewOperationException> {
            reviews.add(review)
        }
    }

    @Test
    fun `리뷰를 성공적으로 제거하면 true 를 반환한다`() {
        val sameReviewNo = "123"

        val reviews = BookReviews(mutableListOf(
                BookReview.CommentBookReview(
                        no = sameReviewNo,
                        reviewer = BookReviewer(
                                no = "456",
                                name = "루피"
                        ),
                        score = BookReviewScore.BAD,
                        range = BookReviewRange.A_LITTLE,
                        content = "좋아요"
                )
        ))

        val review = BookReview.CommentBookReview(
                no = sameReviewNo,
                reviewer = BookReviewer(
                        no = "456",
                        name = "루피"
                ),
                score = BookReviewScore.BAD,
                range = BookReviewRange.A_LITTLE,
                content = "좋아요"
        )

        val result = reviews.delete(review)

        assertTrue(result)
    }
}