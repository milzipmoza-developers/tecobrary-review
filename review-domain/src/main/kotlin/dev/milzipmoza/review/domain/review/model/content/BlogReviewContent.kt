package dev.milzipmoza.review.domain.review.model.content

import dev.milzipmoza.review.domain.review.model.ReviewDisplay

class BlogReviewContent(
        private val url: BlogReviewUrl,
        private val content: String = "",
        override var display: ReviewDisplay = ReviewDisplay.PENDING
) : ReviewContent {

    val fullUrl: String
        get() = url.toUrl()

    fun crawlFromBlog(content: String) = editContent(content)

    override fun editContent(content: String) =
            when {
                content.isBlank() -> throw ReviewContentOperationException("리뷰 내용을 확인해주세요.")
                else -> BlogReviewContent(this.url, content, this.display)
            }

    override fun changeDisplay(display: ReviewDisplay): BlogReviewContent {
        return BlogReviewContent(
                url = this.url,
                content = this.content,
                display = display
        )
    }

    override fun fullContent() = content

    override fun summarizeLong() = ReviewContentSummarizePolicy.applyLongContent(this.content)

    override fun extractInline() = ReviewContentSummarizePolicy.applyInlineContent(this.content)
}