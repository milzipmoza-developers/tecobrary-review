package dev.milzipmoza.review.domain.mark

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CountedMarkTest : StringSpec({

    "count 에 따라 내림차순으로 정렬된다." {
        val countMarks = listOf(
                CountedMark(20, MarkType.LIKE, MarkBook("1")),
                CountedMark(40, MarkType.LIKE, MarkBook("1")),
                CountedMark(1, MarkType.LIKE, MarkBook("1")),
                CountedMark(9, MarkType.LIKE, MarkBook("1")),
                CountedMark(100, MarkType.LIKE, MarkBook("1"))
        )

        val sorted = countMarks.sortedDescending()

        sorted[0].count shouldBe 100
        sorted[1].count shouldBe 40
        sorted[2].count shouldBe 20
        sorted[3].count shouldBe 9
        sorted[4].count shouldBe 1
    }

    "count 에 따라 오름차순으로 정렬된다." {
        val countMarks = listOf(
                CountedMark(20, MarkType.LIKE, MarkBook("1")),
                CountedMark(40, MarkType.LIKE, MarkBook("1")),
                CountedMark(1, MarkType.LIKE, MarkBook("1")),
                CountedMark(9, MarkType.LIKE, MarkBook("1")),
                CountedMark(100, MarkType.LIKE, MarkBook("1"))
        )

        val sorted = countMarks.sorted()

        sorted[0].count shouldBe 1
        sorted[1].count shouldBe 9
        sorted[2].count shouldBe 20
        sorted[3].count shouldBe 40
        sorted[4].count shouldBe 100
    }
})