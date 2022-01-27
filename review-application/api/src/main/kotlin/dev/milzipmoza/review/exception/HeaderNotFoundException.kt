package dev.milzipmoza.review.exception

class HeaderNotFoundException(
        message: String? = null,
        val fieldName: String
) : RuntimeException(message)