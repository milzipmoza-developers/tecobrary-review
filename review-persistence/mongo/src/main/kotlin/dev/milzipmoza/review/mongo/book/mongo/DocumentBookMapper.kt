package dev.milzipmoza.review.mongo.book.mongo

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.category.BookCategoryImageUrl
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl
import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import dev.milzipmoza.review.domain.book.model.tag.BookTag
import dev.milzipmoza.review.domain.book.model.tag.BookTags
import org.bson.types.ObjectId

object DocumentBookMapper {

    fun map(documentBook: DocumentBook, documentBookDetail: DocumentBookDetail, documentBookTags: DocumentBookTags) =
            Book(
                    isbn = documentBook.isbn,
                    detail = map(documentBookDetail),
                    category = map(documentBook.category),
                    tags = map(documentBookTags)
            )

    private fun map(documentBookDetail: DocumentBookDetail) = BookDetail(
            image = map(documentBookDetail.image),
            title = documentBookDetail.title,
            publisher = documentBookDetail.publisher,
            author = documentBookDetail.author,
            locale = BookLanguage.valueOf(documentBookDetail.locale),
            publishDate = documentBookDetail.publishDate,
            description = documentBookDetail.description,
    )

    private fun map(image: DocumentBookDetailImage) = BookImageUrl(
            host = image.host,
            path = image.path
    )

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

    private fun map(documentBookTags: DocumentBookTags) =
            BookTags(documentBookTags.tags.map { BookTag(it.no, it.name, it.colorCode) }.toSet())

    private fun map(image: DocumentBookCategoryImage) = BookCategoryImageUrl(
            host = image.host,
            path = image.path
    )

    fun map(bookDetail: BookDetail, detailMappingId: ObjectId) =
            DocumentBookDetail(
                    id = detailMappingId,
                    title = bookDetail.title,
                    publisher = bookDetail.publisher,
                    author = bookDetail.author,
                    image = DocumentBookDetailImage(
                            host = bookDetail.image.host,
                            path = bookDetail.image.path
                    ),
                    locale = bookDetail.locale.toString(),
                    publishDate = bookDetail.publishDate,
                    description = bookDetail.description
            )

    fun map(book: Book, detailMappingId: ObjectId, tagsMappingId: ObjectId) =
            DocumentBook(
                    isbn = book.isbn,
                    category = map(book.category),
                    tagsMappingId = tagsMappingId,
                    detailMappingId = detailMappingId
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

    private fun map(bookCategory: BookCategory.EnrolledBookCategory) =
            DocumentBookCategoryImage(
                    host = bookCategory.imageUrl.host,
                    path = bookCategory.imageUrl.path
            )

    fun map(bookDetail: BookDetail) =
            DocumentBookDetail(
                    title = bookDetail.title,
                    publisher = bookDetail.publisher,
                    author = bookDetail.author,
                    image = DocumentBookDetailImage(
                            host = bookDetail.image.host,
                            path = bookDetail.image.path
                    ),
                    locale = bookDetail.locale.toString(),
                    publishDate = bookDetail.publishDate,
                    description = bookDetail.description
            )

    fun map(bookTags: BookTags) =
            DocumentBookTags(
                    tags = bookTags.map { DocumentBookTag(no = it.no, name = it.name, colorCode = it.colorCode) }
                            .toSet()
            )

    fun map(bookTags: BookTags, tagsMappingId: ObjectId)=
            DocumentBookTags(
                    id = tagsMappingId,
                    tags = bookTags.map { DocumentBookTag(no = it.no, name = it.name, colorCode = it.colorCode) }
                            .toSet()
            )
}