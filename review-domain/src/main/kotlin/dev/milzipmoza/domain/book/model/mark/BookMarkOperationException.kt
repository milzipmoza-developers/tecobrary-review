package dev.milzipmoza.domain.book.model.mark

import dev.milzipmoza.domain.book.BookOperationException

class BookMarkOperationException(override val message: String?) : BookOperationException(message, null) {
}