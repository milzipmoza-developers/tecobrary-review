package dev.milzipmoza.domain.review.model.content

class CommentReviewContent(
        private val content: String
) : ReviewContent {

    override fun editContent(content: String) =
            when {
                content.isBlank() -> throw ReviewContentOperationException("리뷰 내용을 확인해주세요.")
                else -> CommentReviewContent(content)
            }

    override fun fullContent() = content

    override fun summarizeLong() = ReviewContentSummarizePolicy.applyLongContent(content)

    override fun extractInline() = ReviewContentSummarizePolicy.applyInlineContent(this.content)
}