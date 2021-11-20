package dev.milzipmoza.review.domain.review.model

enum class ReviewScore(
        private val displayName: String,
        private val score: Int
) {
    BAD("괜히 읽었어요", 1),
    NOT_GOOD("잘 모르겠어요", 2),
    SO_SO("보통이예요", 3),
    NOT_BAD("읽어볼만 해요", 4),
    BEST("최고예요", 5)
    ;
}