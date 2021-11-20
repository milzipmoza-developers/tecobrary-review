package dev.milzipmoza.domain.member

open class MemberOperationException(
        override val message: String?,
        override val cause: Throwable? = null
) : RuntimeException(message, cause)