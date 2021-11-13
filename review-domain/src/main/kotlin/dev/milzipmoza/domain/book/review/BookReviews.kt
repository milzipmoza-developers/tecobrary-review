package dev.milzipmoza.domain.book.review

class BookReviews(
        private val reviews: MutableList<BookReview> = mutableListOf()
) {
    fun add(review: BookReview): Boolean {
        val hasAlready = reviews.any { it.sameAs(review) }

        if (hasAlready) {
            throw BookReviewOperationException("이미 추가된 리뷰입니다.")
        }

        return reviews.add(review)
    }

    fun delete(review: BookReview): Boolean {
        if (reviews.isEmpty()) {
            throw BookReviewOperationException("삭제할 리뷰가 없습니다.")
        }

        val removeReview = reviews.find { it.sameAs(review) }
                ?: throw BookReviewOperationException("이미 리뷰를 삭제하셨습니다.")

        return reviews.remove(removeReview)
    }
}
