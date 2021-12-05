package dev.milzipmoza.review.domain.mark

import dev.milzipmoza.review.domain.Value

class MarkMember(
        val no: String
) : Value<MarkMember> {

    override fun sameAs(other: MarkMember) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MarkMember

        if (no != other.no) return false

        return true
    }

    override fun hashCode(): Int {
        return no.hashCode()
    }
}
