package dev.milzipmoza.review.domain.category

open class CategoryOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)