package dev.milzipmoza.review.domain.review

open class ReviewOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)