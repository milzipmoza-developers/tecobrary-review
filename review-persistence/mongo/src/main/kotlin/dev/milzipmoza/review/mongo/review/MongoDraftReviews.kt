package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.DraftReviews
import dev.milzipmoza.review.domain.review.model.DraftReview
import dev.milzipmoza.review.domain.review.model.DraftReviewMember
import dev.milzipmoza.review.domain.review.model.ReviewBook
import dev.milzipmoza.review.domain.review.model.ReviewKeyword
import dev.milzipmoza.review.domain.review.model.ReviewReadRange
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import org.springframework.stereotype.Repository

@Repository
class MongoDraftReviews(
        private val mongoDraftReviewRepository: MongoDraftReviewRepository
) : DraftReviews {

    override fun get(no: String): DraftReview {
        val document = mongoDraftReviewRepository.findByNo(no)
                ?: throw DocumentNotFoundException("임시저장된 리뷰를 찾을 수 없습니다.")

        return when {
            document.range == null && document.keyword == null -> {
                DraftReview.DraftReviewFirstStep(
                        no = document.no,
                        member = DraftReviewMember(
                                no = document.member.no,
                                deviceId = document.member.deviceId
                        ),
                        book = ReviewBook(
                                isbn = document.book.isbn,
                                title = document.book.title
                        )
                )
            }
            document.range != null && document.keyword == null -> {
                DraftReview.DraftReviewSecondStep(
                        no = document.no,
                        member = DraftReviewMember(
                                no = document.member.no,
                                deviceId = document.member.deviceId
                        ),
                        book = ReviewBook(
                                isbn = document.book.isbn,
                                title = document.book.title
                        ),
                        range = ReviewReadRange.valueOf(document.range)
                )
            }
            document.range != null && document.keyword != null -> {
                DraftReview.DraftReviewThirdStep(
                        no = document.no,
                        member = DraftReviewMember(
                                no = document.member.no,
                                deviceId = document.member.deviceId
                        ),
                        book = ReviewBook(
                                isbn = document.book.isbn,
                                title = document.book.title
                        ),
                        range = ReviewReadRange.valueOf(document.range),
                        keyword = ReviewKeyword(
                                content = ReviewKeyword.Content.valueOf(document.keyword.content),
                                informative = ReviewKeyword.Informative.valueOf(document.keyword.informative),
                                readMore = document.keyword.readMore?.let { ReviewKeyword.ReadMore.valueOf(it) },
                                selectables = document.keyword.selectables.map { ReviewKeyword.Selectable.valueOf(it) }.toSet(),
                        )
                )
            }
            else -> throw IllegalStateException("문제가 발생하였습니다.")
        }
    }
}