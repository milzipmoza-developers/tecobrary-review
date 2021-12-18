package dev.milzipmoza.review.mongo.book.mongo

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl
import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import org.bson.types.ObjectId

object DocumentBookMapper {

    fun map(documentBook: DocumentBook, documentBookDetail: DocumentBookDetail) =
            Book(
                    isbn = documentBook.isbn,
                    detail = BookDetail(
                            image = BookImageUrl(
                                    host = documentBookDetail.image.host,
                                    path = documentBookDetail.image.path
                            ),
                            title = documentBookDetail.title,
                            publisher = documentBookDetail.publisher,
                            author = documentBookDetail.author,
                            locale = BookLanguage.valueOf(documentBookDetail.locale),
                            publishDate = documentBookDetail.publishDate,
                            description = documentBookDetail.description,
                    )
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

    fun map(book: Book, detailMappingId: ObjectId) =
            DocumentBook(
                    isbn = book.isbn,
                    detailMappingId = detailMappingId
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
}