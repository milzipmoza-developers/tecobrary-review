package dev.milzipmoza.review.mongo.book.domain

import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookMapper
import dev.milzipmoza.review.mongo.book.mongo.MongoBookDetailRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookTagsRepository
import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.stereotype.Repository

@Repository
class MongoBookOperation(
        private val mongoBookRepository: MongoBookRepository,
        private val mongoBookDetailRepository: MongoBookDetailRepository,
        private val mongoBookTagsRepository: MongoBookTagsRepository
) : BookOperation {

    private val log = Logger.of(this)

    override fun save(book: Book): Boolean {
        val documentBookTags = DocumentBookMapper.map(book.tags)
        val savedBookTags = mongoBookTagsRepository.save(documentBookTags)
        log.info("[MongoBookOperation][{}] succeed saving book tags={}", book.isbn, savedBookTags.id)

        val documentBookDetail = DocumentBookMapper.map(book.detail)
        val savedBookDetail = mongoBookDetailRepository.save(documentBookDetail)
        log.info("[MongoBookOperation][{}] succeed saving book detail={}", book.isbn, savedBookDetail.id)

        val documentBook = DocumentBookMapper.map(book, savedBookDetail.id, documentBookTags.id)
        val savedBook = mongoBookRepository.save(documentBook)
        log.info("[MongoBookOperation][{}] succeed saving book={}", book.isbn, savedBook.id)

        return true
    }

    override fun edit(book: Book): Boolean {
        val documentBook = mongoBookRepository.findByIsbn(book.isbn)
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없습니다.")
        documentBook.category = DocumentBookMapper.map(book.category)
        val updatedDocumentBook = mongoBookRepository.save(documentBook)
        log.info("[MongoBookOperation][{}] succeed updating book={}", book.isbn, updatedDocumentBook.id)

        val documentBookDetail = DocumentBookMapper.map(book.detail, documentBook.detailMappingId)
        val updatedBookDetail = mongoBookDetailRepository.save(documentBookDetail)
        log.info("[MongoBookOperation][{}] succeed updating book detail={}", book.isbn, updatedBookDetail.id)

        val documentBookTags = DocumentBookMapper.map(book.tags, documentBook.tagsMappingId)
        val updatedBookTags = mongoBookTagsRepository.save(documentBookTags)
        log.info("[MongoBookOperation][{}] succeed updating book tags={}", book.isbn, updatedBookDetail.id)
        return true
    }
}