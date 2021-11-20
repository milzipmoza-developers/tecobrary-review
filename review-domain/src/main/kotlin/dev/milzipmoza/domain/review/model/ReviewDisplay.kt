package dev.milzipmoza.domain.review.model

enum class ReviewDisplay(
        val displayName: String
) {
    ON_DISPLAY("게시중"),
    BANNED("차단"),
    DELETED("삭제"),

}
