package dev.milzipmoza.review.domain.search

open class SearchOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)