package dev.milzipmoza.review.domain.book.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.Url
import dev.milzipmoza.review.domain.Value
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.detail.BookDetail

class Book(
        val isbn: String,
        val detail: BookDetail,
        val category: BookCategory = BookCategory.hasNoCategory(),
) : Entity<Book> {

    override fun getId() = isbn

    internal fun edit(bookDetail: BookDetail): Book {
        return Book(
                isbn = this.isbn,
                detail = this.detail.edit(bookDetail),
                category = this.category
        )
    }

    internal fun edit(category: BookCategory): Book {
        return Book(
                isbn = this.isbn,
                detail = this.detail,
                category = category
        )
    }
}