package dev.milzipmoza.domain.book.category

import dev.milzipmoza.domain.Value

class BookCategory(
        val color: String,
        val name: String
) : Value<BookCategory> {

    override fun sameAs(other: Value<BookCategory>): Boolean {
        return this == other
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookCategory

        if (color != other.color) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = color.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
