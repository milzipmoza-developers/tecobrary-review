package dev.milzipmoza.domain.review.model

enum class ReviewType(
        val displayName: String
) {
    COMMENT("코멘트 리뷰"),
    BLOG_REFERENCE("블로그 리뷰"),
}
