package dev.milzipmoza.review.domain.review.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class EnrolledReviewsTest : StringSpec({

    "작성된 리뷰가 없으면 모든 범위를 반환한다" {
        val enrolledReviews = EnrolledReviews(listOf())

        val availableRanges = enrolledReviews.availableRanges()

        availableRanges.size shouldBe ReviewReadRange.values().size
    }

    "작성된 리뷰가 READ_INTRODUCTION 이면 READ_INTRODUCTION 를 제외한 나머지를 반환한다" {
        val enrolledReviews = EnrolledReviews(listOf(
                Review.SimpleReview(
                        member = ReviewMember(no = ""),
                        book = ReviewBook(isbn = "", title = ""),
                        range = ReviewReadRange.READ_INTRODUCTION,
                        keyword = ReviewKeyword(
                                content = ReviewKeyword.Content.EASY,
                                informative = ReviewKeyword.Informative.MUCH,
                                readMore = null,
                                selectables = setOf()
                        )
                )
        ))

        val availableRanges = enrolledReviews.availableRanges()

        availableRanges.size shouldBe 4
        availableRanges shouldContain ReviewReadRange.A_LITTLE
        availableRanges shouldContain ReviewReadRange.ONE_CHAPTER
        availableRanges shouldContain ReviewReadRange.MULTIPLE_CHAPTERS
        availableRanges shouldContain ReviewReadRange.READ_ALL
    }

    "작성된 리뷰가 MULTIPLE_CHAPTERS 이면 READ_ALL 만 반환한다" {
        val enrolledReviews = EnrolledReviews(listOf(
                Review.SimpleReview(
                        member = ReviewMember(no = ""),
                        book = ReviewBook(isbn = "", title = ""),
                        range = ReviewReadRange.MULTIPLE_CHAPTERS,
                        keyword = ReviewKeyword(
                                content = ReviewKeyword.Content.EASY,
                                informative = ReviewKeyword.Informative.MUCH,
                                readMore = null,
                                selectables = setOf()
                        )
                )
        ))

        val availableRanges = enrolledReviews.availableRanges()

        availableRanges.size shouldBe 1
        availableRanges shouldContain ReviewReadRange.READ_ALL
    }
})