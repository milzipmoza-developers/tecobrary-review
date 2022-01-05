package dev.milzipmoza.review.domain.book.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.Url
import dev.milzipmoza.review.domain.Value
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.tag.BookTag
import dev.milzipmoza.review.domain.book.model.tag.BookTags

class Book(
        val isbn: String,
        val detail: BookDetail,
        val category: BookCategory = BookCategory.hasNoCategory(),
        val tags: BookTags = BookTags()
) : Entity<Book> {

    override fun getId() = isbn

    internal fun edit(bookDetail: BookDetail): Book {
        return Book(
                isbn = this.isbn,
                detail = this.detail.edit(bookDetail),
                category = this.category,
                tags = this.tags
        )
    }

    internal fun edit(category: BookCategory): Book {
        return Book(
                isbn = this.isbn,
                detail = this.detail,
                category = category,
                tags = this.tags
        )
    }

    internal fun add(vararg bookTag: BookTag): Book {
        return Book(
                isbn, detail, category,
                tags = tags.add(bookTag)
        )
    }

    internal fun remove(bookTag: BookTag): Book {
        return Book(
                isbn, detail, category,
                tags = tags.remove(bookTag)
        )
    }
}