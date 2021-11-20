package dev.milzipmoza.review.domain.review.model.content

class BlogReviewContent(
        private val url: BlogReviewUrl,
        private val content: String = ""
) : ReviewContent {

    val fullUrl: String
        get() = url.toUrl()

    fun crawlFromBlog(content: String) = editContent(content)

    override fun editContent(content: String) =
            when {
                content.isBlank() -> throw ReviewContentOperationException("리뷰 내용을 확인해주세요.")
                else -> BlogReviewContent(this.url, content)
            }

    override fun fullContent() = content

    override fun summarizeLong() = ReviewContentSummarizePolicy.applyLongContent(this.content)

    override fun extractInline() = ReviewContentSummarizePolicy.applyInlineContent(this.content)
}