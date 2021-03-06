package dev.milzipmoza.review.domain.review.model

import dev.milzipmoza.review.domain.Value

class ReviewBook(
        val isbn: String,
        val title: String
) : Value<ReviewBook> {

    override fun sameAs(other: ReviewBook): Boolean = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReviewBook

        if (isbn != other.isbn) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isbn.hashCode()
        result = 31 * result + title.hashCode()
        return result
    }
}
