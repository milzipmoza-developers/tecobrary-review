package dev.milzipmoza.domain.book.review

import dev.milzipmoza.domain.Entity
import dev.milzipmoza.domain.book.reviewer.BookReviewer

sealed interface BookReview: Entity<BookReview> {

    val no: String
    val reviewer: BookReviewer
    val score: BookReviewScore
    val range: BookReviewRange
    val type: BookReviewType

    override fun getId() = no

    fun sameAs(other: BookReview): Boolean {
        return super.sameAs(other) && this.type == other.type
    }

    class CommentBookReview(
        override val no: String,
        override val reviewer: BookReviewer,
        override val score: BookReviewScore,
        override val range: BookReviewRange,
        val content: String
    ) : BookReview {

        override val type = BookReviewType.COMMENT
    }

    class BlogBookReview(
        override val no: String,
        override val reviewer: BookReviewer,
        override val score: BookReviewScore,
        override val range: BookReviewRange,
        val fullUrl: String,
        val briefContent: String
    ) : BookReview {

        override val type = BookReviewType.BLOG_REFERENCE
    }
}