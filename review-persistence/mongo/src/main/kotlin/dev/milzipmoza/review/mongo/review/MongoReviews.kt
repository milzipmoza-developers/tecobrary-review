package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.Reviews
import dev.milzipmoza.review.domain.review.model.EnrolledReviews
import dev.milzipmoza.review.domain.review.model.Review
import dev.milzipmoza.review.domain.review.model.ReviewBook
import dev.milzipmoza.review.domain.review.model.ReviewKeyword
import dev.milzipmoza.review.domain.review.model.ReviewMember
import dev.milzipmoza.review.domain.review.model.ReviewReadRange
import org.springframework.stereotype.Repository

@Repository
class MongoReviews(
        private val mongoReviewRepository: MongoReviewRepository
) : Reviews {

    override fun getAll(memberNo: String, isbn: String): EnrolledReviews {
        val documents = mongoReviewRepository.findAllByMemberNoAndBookNo(memberNo, isbn)

        val reviews = documents.map {
            Review.SimpleReview(
                    no = it.id.toHexString(),
                    member = ReviewMember(
                            no = it.member.no,
                    ),
                    book = ReviewBook(
                            isbn = it.book.isbn,
                            title = it.book.title
                    ),
                    range = ReviewReadRange.valueOf(it.range),
                    keyword = reviewKeyword(it.keyword)
            )
        }

        return EnrolledReviews(reviews)
    }

    override fun count(bookIsbn: String): Long {
        return mongoReviewRepository.countAllByBookIsbn(bookIsbn)
    }

    private fun reviewKeyword(keyword: DocumentReviewKeyword) = ReviewKeyword(
            content = ReviewKeyword.Content.valueOf(keyword.content),
            informative = ReviewKeyword.Informative.valueOf(keyword.informative),
            readMore = keyword.readMore?.let { ReviewKeyword.ReadMore.valueOf(it) },
            selectables = keyword.selectables.map { ReviewKeyword.Selectable.valueOf(it) }.toSet()
    )
}