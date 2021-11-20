package dev.milzipmoza.review.domain.book

open class BookOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)