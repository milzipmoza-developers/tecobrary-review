package dev.milzipmoza.domain.book.review

import dev.milzipmoza.domain.book.BookOperationException

class BookReviewOperationException(override val message: String?) : BookOperationException(message, null)