package dev.milzipmoza.review.domain.review.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.util.UUID

class DraftReviewTest : StringSpec({

    "range 와 keyword 가 null 이면 리뷰의 첫번째 단계이다. (도서 선택 후)" {
        val firstStep = DraftReview.DraftReviewFirstStep(
                no = "1",
                member = DraftReviewMember(
                        no = "member_no_00001",
                        deviceId = UUID.randomUUID().toString()
                ),
                book = ReviewBook(
                        isbn = "9788979148862",
                        title = "클린 아키텍처"
                )
        )

        firstStep.range shouldBe null
        firstStep.keyword shouldBe null
    }

    "range 가 null 이 아니고 keyword 가 null 이면 리뷰의 두번째 단계이다. (읽은 양 선택 후)" {
        val firstStep = DraftReview.DraftReviewFirstStep(
                no = "1",
                member = DraftReviewMember(
                        no = "member_no_00001",
                        deviceId = UUID.randomUUID().toString()
                ),
                book = ReviewBook(
                        isbn = "9788979148862",
                        title = "클린 아키텍처"
                )
        )

        val secondStep = firstStep.selectRange(ReviewReadRange.MULTIPLE_CHAPTERS)

        secondStep.range shouldNotBe null
        secondStep.keyword shouldBe null
    }

    "두번째 단계 (읽은 양 선택 후) 에서 Range 를 초기화 하면 다시 첫번째 스텝으로 돌아간다." {
        val firstStep = DraftReview.DraftReviewFirstStep(
                no = "1",
                member = DraftReviewMember(
                        no = "member_no_00001",
                        deviceId = UUID.randomUUID().toString()
                ),
                book = ReviewBook(
                        isbn = "9788979148862",
                        title = "클린 아키텍처"
                )
        )

        val secondStep = firstStep.selectRange(ReviewReadRange.MULTIPLE_CHAPTERS)
        val initRange = secondStep.initRange()

        initRange.range shouldBe null
        initRange.keyword shouldBe null
    }

    "range 와 keyword 가 null 이 아니면 리뷰의 세번째 단계이다. (키워드 선택 후)" {
        val firstStep = DraftReview.DraftReviewFirstStep(
                no = "1",
                member = DraftReviewMember(
                        no = "member_no_00001",
                        deviceId = UUID.randomUUID().toString()
                ),
                book = ReviewBook(
                        isbn = "9788979148862",
                        title = "클린 아키텍처"
                )
        )

        val secondStep = firstStep.selectRange(ReviewReadRange.MULTIPLE_CHAPTERS)
        val thirdStep = secondStep.selectKeyword(
                ReviewKeyword(
                        content = ReviewKeyword.Content.EASY,
                        informative = ReviewKeyword.Informative.MUCH,
                        readMore = ReviewKeyword.ReadMore.NO_MORE,
                        selectables = setOf()
                )
        )

        thirdStep.range shouldNotBe null
        thirdStep.keyword shouldNotBe null
    }

    "세번째 단계 (키워드 선택 후) 에서 Range 를 초기화 하면 다시 첫번째 스텝으로 돌아간다." {
        val firstStep = DraftReview.DraftReviewFirstStep(
                no = "1",
                member = DraftReviewMember(
                        no = "member_no_00001",
                        deviceId = UUID.randomUUID().toString()
                ),
                book = ReviewBook(
                        isbn = "9788979148862",
                        title = "클린 아키텍처"
                )
        )

        val secondStep = firstStep.selectRange(ReviewReadRange.MULTIPLE_CHAPTERS)
        val thirdStep = secondStep.selectKeyword(
                ReviewKeyword(
                        content = ReviewKeyword.Content.EASY,
                        informative = ReviewKeyword.Informative.MUCH,
                        readMore = ReviewKeyword.ReadMore.NO_MORE,
                        selectables = setOf()
                )
        )
        val initRange = thirdStep.initRange()

        initRange.range shouldBe null
        initRange.keyword shouldBe null
    }

    "본인의 리뷰가 아니면 owned"
})