package dev.milzipmoza.domain.book.model.review

enum class BookReviewType(
        val displayName: String
) {
    COMMENT("코멘트 리뷰"),
    BLOG_REFERENCE("블로그 리뷰"),
}
