package dev.milzipmoza.review.domain.book.model.review

import dev.milzipmoza.review.domain.book.BookOperationException

class BookReviewOperationException(override val message: String?) : BookOperationException(message, null)