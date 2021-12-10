package dev.milzipmoza.review.domain.mark

enum class MarkType(
        val displayName: String
) {
    LIKE("좋아요"),
    FAVORITE("즐겨찾기")
    ;

    companion object {
        fun valueOfIgnoreCases(value: String): MarkType {
            return values().find { it.name == value.uppercase() }
                    ?: throw IllegalArgumentException("타입을 확인해주세요.")
        }
    }
}