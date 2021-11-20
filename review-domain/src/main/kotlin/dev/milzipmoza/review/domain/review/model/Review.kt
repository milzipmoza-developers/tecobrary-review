package dev.milzipmoza.review.domain.review.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.review.model.book.ReviewBook
import dev.milzipmoza.review.domain.review.model.content.BlogReviewContent
import dev.milzipmoza.review.domain.review.model.content.BlogReviewUrl
import dev.milzipmoza.review.domain.review.model.content.CommentReviewContent
import dev.milzipmoza.review.domain.review.model.content.ReviewContent
import dev.milzipmoza.review.domain.review.model.member.ReviewMember

sealed interface Review : Entity<Review> {

    val no: String
    val member: ReviewMember
    val book: ReviewBook
    val score: ReviewScore
    val range: ReviewReadRange
    val type: ReviewType
    var display: ReviewDisplay
    val content: ReviewContent

    override fun getId() = no

    fun changeDisplay(display: ReviewDisplay) {
        this.display = display
    }

    class CommentReview(
            override val no: String,
            override val member: ReviewMember,
            override val book: ReviewBook,
            override val score: ReviewScore,
            override val range: ReviewReadRange,
            override var display: ReviewDisplay,
            override val content: CommentReviewContent
    ) : Review {
        override val type: ReviewType = ReviewType.COMMENT
    }

    class PendedBlogReview(
            override val no: String,
            override val member: ReviewMember,
            override val book: ReviewBook,
            override val score: ReviewScore,
            override val range: ReviewReadRange,
            override var display: ReviewDisplay,
            blogUrl: String

    ) : Review {
        override val content = BlogReviewContent(BlogReviewUrl(blogUrl))
        override val type: ReviewType = ReviewType.BLOG_REFERENCE

        fun confirm(content: String) =
                BlogReview(
                        no = this.no,
                        member = this.member,
                        book = this.book,
                        score = this.score,
                        range = this.range,
                        display = this.display,
                        content = this.content.crawlFromBlog(content)
                )
    }

    class BlogReview(
            override val no: String,
            override val member: ReviewMember,
            override val book: ReviewBook,
            override val score: ReviewScore,
            override val range: ReviewReadRange,
            override var display: ReviewDisplay,
            override val content: BlogReviewContent
    ) : Review {
        override val type: ReviewType = ReviewType.BLOG_REFERENCE
    }
}
