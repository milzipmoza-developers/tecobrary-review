package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.mark.CountedMark
import dev.milzipmoza.review.domain.mark.MarkAggregation
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkAggregator
import org.springframework.stereotype.Repository

@Repository
class MongoMarkAggregation(
        private val mongoMarkAggregator: MongoMarkAggregator
) : MarkAggregation {

    override fun getTop(type: MarkType, count: Int): List<CountedMark> {
        val findTop = mongoMarkAggregator.findTop(type.name, count)
        return findTop.map { CountedMark(it.count, type, MarkBook(it.bookNo)) }
    }
}