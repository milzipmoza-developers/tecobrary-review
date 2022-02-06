package dev.milzipmoza.review.exception


class UnauthorizedMemberException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)
