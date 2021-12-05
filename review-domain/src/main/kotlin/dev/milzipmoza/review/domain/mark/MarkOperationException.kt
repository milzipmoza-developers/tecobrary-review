package dev.milzipmoza.review.domain.mark

open class MarkOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)