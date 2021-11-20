package dev.milzipmoza.review.domain.book.model.reviewer

import dev.milzipmoza.review.domain.Entity

class BookReviewer(
        private val no: String,
        val name: String
) : Entity<BookReviewer> {

    override fun getId() = no
}
