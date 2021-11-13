package dev.milzipmoza.domain.category

open class CategoryOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)