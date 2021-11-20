package dev.milzipmoza.review.domain.book.model.mark

import dev.milzipmoza.review.domain.book.BookOperationException

class BookMarkOperationException(override val message: String?) : BookOperationException(message, null) {
}