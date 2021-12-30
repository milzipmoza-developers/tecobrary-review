package dev.milzipmoza.review.domain.book.model.category

import dev.milzipmoza.review.domain.Url
import dev.milzipmoza.review.domain.Value

sealed interface BookCategory {

    class EnrolledBookCategory(
            val no: String,
            val name: String,
            val imageUrl: BookCategoryImageUrl
    ) : Value<EnrolledBookCategory>, BookCategory {
        val fullImageUrl: String
            get() = imageUrl.toUrl()

        override fun sameAs(other: EnrolledBookCategory) = this == other

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as EnrolledBookCategory

            if (no != other.no) return false
            if (name != other.name) return false
            if (imageUrl != other.imageUrl) return false

            return true
        }

        override fun hashCode(): Int {
            var result = no.hashCode()
            result = 31 * result + name.hashCode()
            result = 31 * result + imageUrl.hashCode()
            return result
        }
    }

    class NoBookCategory : Value<NoBookCategory>, BookCategory {

        override fun sameAs(other: NoBookCategory) = this == other

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    companion object {
        fun hasNoCategory() = NoBookCategory()
    }
}