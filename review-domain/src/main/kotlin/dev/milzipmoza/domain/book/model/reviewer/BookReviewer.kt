package dev.milzipmoza.domain.book.model.reviewer

import dev.milzipmoza.domain.Entity

class BookReviewer(
        private val no: String,
        val name: String
) : Entity<BookReviewer> {

    override fun getId() = no
}
