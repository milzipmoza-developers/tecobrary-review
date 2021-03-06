package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.ClientMember
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.review.ReviewOperation
import dev.milzipmoza.review.domain.review.Reviews
import dev.milzipmoza.review.domain.review.model.Review
import dev.milzipmoza.review.domain.review.model.ReviewBook
import dev.milzipmoza.review.domain.review.model.ReviewKeyword
import dev.milzipmoza.review.domain.review.model.ReviewMember
import dev.milzipmoza.review.domain.review.model.ReviewReadRange
import dev.milzipmoza.review.exception.UnauthorizedMemberException
import dev.milzipmoza.review.mongo.DocumentNotFoundException

@ApplicationService
class ReviewSubmitNotUseDraftService(
        private val books: Books,
        private val reviews: Reviews,
        private val reviewOperation: ReviewOperation
) {
    fun submit(clientMember: ClientMember, body: ReviewSubmitDto): Boolean {
        val simpleReview = when (clientMember) {
            is ClientMember.UnknownMember,
            is ClientMember.UnauthenticatedMember -> {
                throw UnauthorizedMemberException("로그인 하시면 리뷰 등록을 마무리할 수 있어요")
            }
            is ClientMember.AuthenticatedMember -> {
                val book = try {
                    books.findBy(body.isbn)
                } catch (e: DocumentNotFoundException) {
                    throw IllegalArgumentException("리뷰를 등록하려는 도서가 없어요")
                }

                val range = ReviewReadRange.valueOf(body.range)
                val enrolledReviews = reviews.getAll(clientMember.memberNo, body.isbn)

                if (enrolledReviews.isNotAvailableToEnroll(range)) {
                    throw IllegalArgumentException("이미 등록한 리뷰예요")
                }

                Review.SimpleReview(
                        member = ReviewMember(no = clientMember.memberNo),
                        book = ReviewBook(isbn = book.isbn, title = book.detail.title),
                        range = range,
                        keyword = ReviewKeyword(
                                content = ReviewKeyword.Content.valueOf(body.content),
                                informative = ReviewKeyword.Informative.valueOf(body.informative),
                                readMore = body.readMore?.let { ReviewKeyword.ReadMore.valueOf(it) },
                                selectables = body.selectables.map { ReviewKeyword.Selectable.valueOf(it) }.toSet()
                        )
                )
            }
        }
        return reviewOperation.save(simpleReview)
    }
}