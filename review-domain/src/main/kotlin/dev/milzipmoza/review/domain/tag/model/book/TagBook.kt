package dev.milzipmoza.review.domain.tag.model.book

import dev.milzipmoza.review.domain.Value

class TagBook(
        val isbn: String
) : Value<TagBook> {

    override fun sameAs(other: TagBook) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TagBook

        if (isbn != other.isbn) return false

        return true
    }

    override fun hashCode(): Int {
        return isbn.hashCode()
    }
}