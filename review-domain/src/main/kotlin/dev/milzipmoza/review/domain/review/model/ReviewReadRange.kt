package dev.milzipmoza.review.domain.review.model

enum class ReviewReadRange(
        val displayOrder: Int,
        val displayName: String
) {
    READ_INTRODUCTION(1, "필요한 책인지 훑어봤어요"),
    A_LITTLE(2, "필요한 부분만 읽어봤어요"),
    ONE_CHAPTER(3, "한 챕터 읽었어요"),
    MULTIPLE_CHAPTERS(4, "여러 챕터 읽었어요"),
    READ_ALL(5, "전부 읽었어요")
    ;

}