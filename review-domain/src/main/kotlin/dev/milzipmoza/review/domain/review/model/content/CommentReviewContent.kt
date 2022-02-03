package dev.milzipmoza.review.domain.review.model.content

import dev.milzipmoza.review.domain.review.model.ReviewDisplay

class CommentReviewContent(
        private val content: String,
        override var display: ReviewDisplay
) : ReviewContent {

    override fun editContent(content: String) =
            when {
                content.isBlank() -> throw ReviewContentOperationException("리뷰 내용을 확인해주세요.")
                else -> CommentReviewContent(content, this.display)
            }

    override fun changeDisplay(display: ReviewDisplay): ReviewContent {
        return CommentReviewContent(
                content = this.content,
                display = display
        )
    }

    override fun fullContent() = content

    override fun summarizeLong() = ReviewContentSummarizePolicy.applyLongContent(content)

    override fun extractInline() = ReviewContentSummarizePolicy.applyInlineContent(this.content)
}