package dev.milzipmoza.review.mongo.book.domain

import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookMapper
import dev.milzipmoza.review.mongo.book.mongo.MongoBookDetailRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.stereotype.Repository

@Repository
class MongoBookOperation(
        private val mongoBookRepository: MongoBookRepository,
        private val mongoBookDetailRepository: MongoBookDetailRepository
) : BookOperation {

    private val log = Logger.of(this)

    override fun save(book: Book): Boolean {
        val documentBookDetail = DocumentBookMapper.map(book.detail)
        val savedBookDetail = mongoBookDetailRepository.save(documentBookDetail)
        log.info("[MongoBookOperation] succeed saving book detail={}", savedBookDetail.id)

        val documentBook = DocumentBookMapper.map(book, savedBookDetail.id)
        val savedBook = mongoBookRepository.save(documentBook)
        log.info("[MongoBookOperation] succeed saving book={}", savedBook.id)

        return true
    }

    override fun edit(book: Book): Boolean {
        val documentBook = mongoBookRepository.findByIsbn(book.isbn)
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없습니다.")
        documentBook.category = DocumentBookMapper.map(book.category)
        val updatedDocumentBook = mongoBookRepository.save(documentBook)
        log.info("[MongoBookOperation] succeed updating book detail={}", updatedDocumentBook.id)

        val documentBookDetail = DocumentBookMapper.map(book.detail, documentBook.detailMappingId)
        val updatedBookDetail = mongoBookDetailRepository.save(documentBookDetail)
        log.info("[MongoBookOperation] succeed updating book detail={}", updatedBookDetail.id)

        return true
    }
}