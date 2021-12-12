package dev.milzipmoza.review.domain.tag

open class TagOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)