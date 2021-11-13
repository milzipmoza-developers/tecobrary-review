package dev.milzipmoza.domain.book

open class BookOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)