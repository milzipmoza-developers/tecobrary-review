package dev.milzipmoza.review.mongo.book.domain

import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl
import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.book.mongo.MongoBookDetailRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import org.springframework.stereotype.Repository

@Repository
class MongoBooks(
        private val mongoBookRepository: MongoBookRepository,
        private val mongoBookDetailRepository: MongoBookDetailRepository
) : Books {

    override fun findBy(isbn: String): Book {
        val documentBook = mongoBookRepository.findByIsbn(isbn)
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없습니다.")

        val documentBookDetail = mongoBookDetailRepository.findById(documentBook.detailMappingId).unwrap()
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없습니다.")

        return Book(
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
    }
}