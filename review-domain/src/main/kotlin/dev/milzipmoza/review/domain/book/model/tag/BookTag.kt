package dev.milzipmoza.review.domain.book.model.tag

import dev.milzipmoza.review.domain.Value

class BookTag(
        val no: String,
        val name: String,
        val colorCode: String
) : Value<BookTag> {

    override fun sameAs(other: BookTag) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookTag

        if (no != other.no) return false
        if (name != other.name) return false
        if (colorCode != other.colorCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = no.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + colorCode.hashCode()
        return result
    }
}