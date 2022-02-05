package dev.milzipmoza.review.domain.review.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class DraftReviewMemberTest : StringSpec({

    "회원번호가 없으면 디바이스 번호로 비교한다." {
        val it = DraftReviewMember(deviceId = "a")
        val other = DraftReviewMember(no = "mem", deviceId = "a")

        it.isNotSameMember(other) shouldBe false
    }

    "회원번호가 있으면 회원번호로 비교한다." {
        val it = DraftReviewMember(no = "mem", deviceId = "b")
        val other = DraftReviewMember(no = "mem", deviceId = "a")

        it.isNotSameMember(other) shouldBe false
    }
})