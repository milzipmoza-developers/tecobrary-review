package dev.milzipmoza.review.domain.mark

interface MarkAggregation {

    fun getTop(type: MarkType, count: Int): List<CountedMark>
}