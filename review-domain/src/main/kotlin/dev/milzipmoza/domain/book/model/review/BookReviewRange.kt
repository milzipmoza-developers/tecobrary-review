package dev.milzipmoza.domain.book.model.review

enum class BookReviewRange(
        val displayOrder: Int,
        val displayName: String
) {
    READ_INTRODUCTION(1, "서론만 읽었어요"),
    A_LITTLE(2, "조금 읽어봤어요"),
    ONE_CHAPTER(3, "한 챕터 읽었어요"),
    MULTIPLE_CHAPTER(4, "여러 챕터 읽었어요"),
    READ_ALL(5, "전부 읽었어요")
    ;

}
