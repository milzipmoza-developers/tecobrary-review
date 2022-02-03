package dev.milzipmoza.review.domain.review.model

enum class ReviewDisplay(
        val displayName: String
) {
    PENDING("확인중"),
    ON_DISPLAY("게시중"),
    BANNED("차단"),
    DELETED("삭제"),

}
