package dev.milzipmoza.review.domain.mark

/**
 * 도메인 모델 중에 분류를 어떻게 할까
 */
class CountedMark(
        val count: Int,
        val type: MarkType,
        val book: MarkBook
) : Comparable<CountedMark> {

    override fun compareTo(other: CountedMark): Int {
        return this.count.compareTo(other.count)
    }
}