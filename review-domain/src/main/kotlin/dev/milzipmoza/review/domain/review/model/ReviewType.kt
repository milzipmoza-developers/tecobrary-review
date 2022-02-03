package dev.milzipmoza.review.domain.review.model

enum class ReviewType(
        val displayName: String
) {
    SIMPLE("간단 리뷰"),
    COMMENT("코멘트 리뷰"),
    BLOG_REFERENCE("블로그 리뷰"),
}
