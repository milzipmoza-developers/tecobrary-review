package dev.milzipmoza.review.domain.review.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.review.ReviewOperationException

interface DraftReview : Entity<Review> {

    val no: String
    val member: DraftReviewMember
    val book: ReviewBook
    val range: ReviewReadRange?
    val keyword: ReviewKeyword?

    override fun getId() = when (no.isNotBlank()) {
        true -> no
        false -> throw ReviewOperationException("아직 식별자가 없습니다.")
    }

    class DraftReviewFirstStep(
            override val no: String,
            override val member: DraftReviewMember,
            override val book: ReviewBook
    ) : DraftReview {
        override val range: ReviewReadRange? = null
        override val keyword: ReviewKeyword? = null

        fun selectRange(range: ReviewReadRange) =
                DraftReviewSecondStep(
                        no = this.no,
                        member = this.member,
                        book = this.book,
                        range = range
                )
    }

    class DraftReviewSecondStep(
            override val no: String,
            override val member: DraftReviewMember,
            override val book: ReviewBook,
            override val range: ReviewReadRange
    ) : DraftReview {
        override val keyword: ReviewKeyword? = null

        fun initRange() =
                DraftReviewFirstStep(
                        no = this.no,
                        member = this.member,
                        book = this.book
                )

        fun selectKeyword(keyword: ReviewKeyword) =
                DraftReviewThirdStep(
                        no = this.no,
                        member = this.member,
                        book = this.book,
                        range = this.range,
                        keyword = keyword
                )
    }

    class DraftReviewThirdStep(
            override val no: String,
            override val member: DraftReviewMember,
            override val book: ReviewBook,
            override val range: ReviewReadRange,
            override val keyword: ReviewKeyword
    ) : DraftReview {
        fun initRange() =
                DraftReviewFirstStep(
                        no = this.no,
                        member = this.member,
                        book = this.book
                )

        fun submitReview(member: ReviewMember) =
                Review.SimpleReview(
                        no = this.no,
                        member = member,
                        book = this.book,
                        range = this.range,
                        keyword = this.keyword
                )
    }
}