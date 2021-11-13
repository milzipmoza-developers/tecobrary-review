package dev.milzipmoza.domain.book.model.reviewer

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BookReviewerTest {

    @Test
    fun `리뷰어 no 가 같으면 같은 리뷰어로 간주한다`() {
        val sameReviewerNo = "123"

        val it = BookReviewer(no = sameReviewerNo, name = "루피")
        val other = BookReviewer(no = sameReviewerNo, name = "상디")

        val sameAs = it.sameAs(other)

        assertTrue(sameAs)
    }

    @Test
    fun `리뷰어 no 가 다르면 다른 리뷰어로 간주한다`() {
        val sameReviewerName = "루피"

        val it = BookReviewer(no = "123", name = sameReviewerName)
        val other = BookReviewer(no = "456", name = sameReviewerName)

        val sameAs = it.sameAs(other)

        assertFalse(sameAs)
    }
}
