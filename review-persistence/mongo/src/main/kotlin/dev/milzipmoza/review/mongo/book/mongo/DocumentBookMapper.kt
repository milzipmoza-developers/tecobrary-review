package dev.milzipmoza.review.mongo.book.mongo

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.category.BookCategoryImageUrl
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl
import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import dev.milzipmoza.review.domain.book.model.tag.BookTags

object DocumentBookMapper {

    fun map(book: Book) =
            DocumentBook(
                    isbn = book.isbn,
                    category = map(book.category),
                    tags = book.tags.map { DocumentBookTag(it.no, it.name, it.colorCode) }.toSet(),
                    detail = DocumentBookDetail(
                            title = book.detail.title,
                            publisher = book.detail.publisher,
                            author = book.detail.author,
                            image = DocumentBookDetailImage(
                                    host = book.detail.image.host,
                                    path = book.detail.image.path
                            ),
                            locale = book.detail.locale.name,
                            publishDate = book.detail.publishDate,
                            description = book.detail.description
                    )
            )

    fun map(bookCategory: BookCategory): DocumentBookCategory? {
        return when (bookCategory) {
            is BookCategory.EnrolledBookCategory -> {
                DocumentBookCategory(
                        no = bookCategory.no,
                        name = bookCategory.name,
                        image = map(bookCategory)
                )
            }
            is BookCategory.NoBookCategory -> {
                null
            }
        }
    }

    private fun map(category: DocumentBookCategory?): BookCategory {
        return when (category) {
            null -> BookCategory.hasNoCategory()
            else -> BookCategory.EnrolledBookCategory(
                    no = category.no,
                    name = category.name,
                    imageUrl = map(category.image)
            )
        }
    }

    private fun map(image: DocumentBookCategoryImage) = BookCategoryImageUrl(
            host = image.host,
            path = image.path
    )

    private fun map(bookCategory: BookCategory.EnrolledBookCategory) =
            DocumentBookCategoryImage(
                    host = bookCategory.imageUrl.host,
                    path = bookCategory.imageUrl.path
            )

    fun map(documentBook: DocumentBook): Book =
            Book(
                    isbn = documentBook.isbn,
                    detail = BookDetail(
                            image = BookImageUrl(
                                    host = documentBook.detail.image.host,
                                    path = documentBook.detail.image.path
                            ),
                            title = documentBook.detail.title,
                            publisher = documentBook.detail.publisher,
                            author = documentBook.detail.author,
                            locale = BookLanguage.valueOf(documentBook.detail.locale),
                            publishDate = documentBook.detail.publishDate,
                            description = documentBook.detail.description),
                    category = map(documentBook.category),
                    tags = BookTags(tags = setOf())

            )

    fun map(documentBooks: List<DocumentBook>): List<Book> {
        return documentBooks.map { map(it) }
    }
}