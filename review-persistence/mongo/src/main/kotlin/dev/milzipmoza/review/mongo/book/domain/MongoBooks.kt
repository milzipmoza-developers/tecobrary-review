package dev.milzipmoza.review.mongo.book.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl
import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.book.mongo.DocumentBook
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookDetail
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookMapper
import dev.milzipmoza.review.mongo.book.mongo.MongoBookDetailRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import dev.milzipmoza.review.mongo.extension.PageRequest
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

        return DocumentBookMapper.map(documentBook, documentBookDetail)
    }

    override fun findBy(pageQuery: PageQuery): PageEntities<Book> {
        val documentBooks = mongoBookRepository.findAll(PageRequest.of(pageQuery))

        val detailIds = documentBooks.content.map { it.detailMappingId }

        val documentBookDetails = mongoBookDetailRepository.findAllByIdIn(detailIds)

        return PageEntities(
                total = documentBooks.totalElements,
                size = documentBooks.size,
                isFirst = documentBooks.isFirst,
                isLast = documentBooks.isLast,
                items = documentBooks.content
                        .map { DocumentBookMapper.map(it, documentBookDetails.find { detail -> detail.id == it.detailMappingId }!!) }
                        .toList()
        )
    }
}