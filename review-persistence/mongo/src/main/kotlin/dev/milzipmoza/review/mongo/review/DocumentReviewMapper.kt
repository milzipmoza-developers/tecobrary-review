package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.model.Review

object DocumentReviewMapper {

    fun map(review: Review) =
            when (review) {
                is Review.SimpleReview -> {
                    DocumentReview(
                            member = DocumentReviewMember(no = review.member.no),
                            book = DocumentReviewBook(
                                    isbn = review.book.isbn,
                                    title = review.book.title
                            ),
                            range = review.range.name,
                            keyword = DocumentReviewKeyword(
                                    content = review.keyword.content.name,
                                    informative = review.keyword.informative.name,
                                    readMore = review.keyword.readMore?.name,
                                    selectables = review.keyword.selectables.map { it.name }.toSet()
                            ),
                    )
                }
                else -> throw UnsupportedOperationException("지원하지 않는 기능입니다")
            }
}
