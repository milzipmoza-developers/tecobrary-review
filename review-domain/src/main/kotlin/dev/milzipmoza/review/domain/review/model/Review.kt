package dev.milzipmoza.review.domain.review.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.review.ReviewOperationException
import dev.milzipmoza.review.domain.review.model.content.BlogReviewContent
import dev.milzipmoza.review.domain.review.model.content.BlogReviewUrl
import dev.milzipmoza.review.domain.review.model.content.CommentReviewContent

sealed interface Review : Entity<Review> {

    val no: String
    val member: ReviewMember
    val book: ReviewBook
    val range: ReviewReadRange
    val keyword: ReviewKeyword
    val type: ReviewType

    override fun getId() = when (no.isNotBlank()) {
        true -> no
        false -> throw ReviewOperationException("아직 식별자가 없습니다.")
    }

    class SimpleReview(
            override val no: String = "",
            override val member: ReviewMember,
            override val book: ReviewBook,
            override val range: ReviewReadRange,
            override val keyword: ReviewKeyword
    ) : Review {
        override val type: ReviewType = ReviewType.SIMPLE

        fun addComment(content: CommentReviewContent): CommentReview {
            return CommentReview(
                    no = this.no,
                    member = this.member,
                    book = this.book,
                    range = this.range,
                    keyword = this.keyword,
                    content = content
            )
        }

        fun addBlogUrl(blogUrl: String): PendedBlogReview {
            return PendedBlogReview(
                    no = this.no,
                    member = this.member,
                    book = this.book,
                    range = this.range,
                    keyword = this.keyword,
                    blogUrl = blogUrl
            )
        }
    }

    class CommentReview(
            override val no: String = "",
            override val member: ReviewMember,
            override val book: ReviewBook,
            override val range: ReviewReadRange,
            val content: CommentReviewContent,
            override val keyword: ReviewKeyword
    ) : Review {
        override val type: ReviewType = ReviewType.COMMENT
    }

    class PendedBlogReview(
            override val no: String = "",
            override val member: ReviewMember,
            override val book: ReviewBook,
            override val range: ReviewReadRange,
            override val keyword: ReviewKeyword,
            blogUrl: String

    ) : Review {
        val content = BlogReviewContent(BlogReviewUrl(blogUrl))
        override val type: ReviewType = ReviewType.BLOG_REFERENCE

        fun confirm(content: String) =
                BlogReview(
                        no = this.no,
                        member = this.member,
                        book = this.book,
                        range = this.range,
                        keyword = this.keyword,
                        content = this.content.crawlFromBlog(content)
                                .changeDisplay(ReviewDisplay.ON_DISPLAY)
                )
    }

    class BlogReview(
            override val no: String = "",
            override val member: ReviewMember,
            override val book: ReviewBook,
            override val range: ReviewReadRange,
            override val keyword: ReviewKeyword,
            val content: BlogReviewContent
    ) : Review {
        override val type: ReviewType = ReviewType.BLOG_REFERENCE
    }
}
