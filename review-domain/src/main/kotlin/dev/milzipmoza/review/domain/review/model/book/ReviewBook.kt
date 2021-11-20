package dev.milzipmoza.review.domain.review.model.book

import dev.milzipmoza.review.domain.Value

class ReviewBook(
        val no: String,
        val title: String,
        val image: String
) : Value<ReviewBook> {

    override fun sameAs(other: ReviewBook): Boolean = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReviewBook

        if (no != other.no) return false
        if (title != other.title) return false
        if (image != other.image) return false

        return true
    }

    override fun hashCode(): Int {
        var result = no.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + image.hashCode()
        return result
    }
}
