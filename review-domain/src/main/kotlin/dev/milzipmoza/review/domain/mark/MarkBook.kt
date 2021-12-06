package dev.milzipmoza.review.domain.mark

import dev.milzipmoza.review.domain.Value

class MarkBook(
        val no: String
) : Value<MarkBook> {

    override fun sameAs(other: MarkBook) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MarkBook

        if (no != other.no) return false

        return true
    }

    override fun hashCode(): Int {
        return no.hashCode()
    }
}
