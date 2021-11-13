package dev.milzipmoza.domain.book.mark

import dev.milzipmoza.domain.Value

class BookMark(
        val type: BookMarkType
) : Value<BookMark> {

    override fun sameAs(other: Value<BookMark>) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookMark

        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}
