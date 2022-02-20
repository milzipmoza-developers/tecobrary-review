package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.model.*

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

    fun map(document: DocumentReview) =
            when (document.type) {
                DocumentReviewType.SIMPLE -> {
                    Review.SimpleReview(
                            no = document.id.toHexString(),
                            member = ReviewMember(
                                    no = document.member.no,
                            ),
                            book = ReviewBook(
                                    isbn = document.book.isbn,
                                    title = document.book.title
                            ),
                            range = ReviewReadRange.valueOf(document.range),
                            keyword = map(document.keyword)
                    )
                }
            }

    private fun map(keyword: DocumentReviewKeyword) = ReviewKeyword(
            content = ReviewKeyword.Content.valueOf(keyword.content),
            informative = ReviewKeyword.Informative.valueOf(keyword.informative),
            readMore = keyword.readMore?.let { ReviewKeyword.ReadMore.valueOf(it) },
            selectables = keyword.selectables.map { ReviewKeyword.Selectable.valueOf(it) }.toSet()
    )
}
