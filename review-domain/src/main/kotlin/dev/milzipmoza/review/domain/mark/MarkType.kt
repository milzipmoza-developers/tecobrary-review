package dev.milzipmoza.review.domain.mark

enum class MarkType(
        val displayName: String
) {
    LIKE("좋아요"),
    FAVORITE("즐겨찾기")
    ;
}