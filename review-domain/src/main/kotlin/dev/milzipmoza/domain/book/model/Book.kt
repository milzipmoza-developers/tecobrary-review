package dev.milzipmoza.domain.book.model

import dev.milzipmoza.domain.Entity
import dev.milzipmoza.domain.book.model.category.BookCategories
import dev.milzipmoza.domain.book.model.category.BookCategory
import dev.milzipmoza.domain.book.model.detail.BookDetail
import dev.milzipmoza.domain.book.model.mark.BookMarks
import dev.milzipmoza.domain.book.model.review.BookReview
import dev.milzipmoza.domain.book.model.review.BookReviews

class Book(
        private val no: String,
        val categories: BookCategories,
        val detail: BookDetail,
        val reviews: BookReviews,
        val markers: BookMarks = BookMarks()
) : Entity<Book> {

    override fun getId() = no

    fun add(category: BookCategory): Boolean {
        return categories.add(category)
    }

    fun remove(category: BookCategory): Boolean {
        return categories.remove(category)
    }

    fun edit(bookDetail: BookDetail): Book {
        return Book(
                no = this.no,
                categories = this.categories,
                detail = this.detail.edit(bookDetail),
                reviews = this.reviews,
                markers = this.markers
        )
    }

    fun add(review: BookReview): Boolean {
        return reviews.add(review)
    }

    fun delete(review: BookReview): Boolean {
        return reviews.delete(review)
    }
}