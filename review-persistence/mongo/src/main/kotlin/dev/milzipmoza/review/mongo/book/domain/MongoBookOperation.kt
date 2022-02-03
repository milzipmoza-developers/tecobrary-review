package dev.milzipmoza.review.mongo.book.domain

import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookMapper
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.stereotype.Repository

@Repository
class MongoBookOperation(
        private val mongoBookRepository: MongoBookRepository
) : BookOperation {

    private val log = Logger.of(this)

    override fun save(book: Book): Boolean {
        val documentBook = DocumentBookMapper.map(book)
        val savedBook = mongoBookRepository.insert(documentBook)
        log.info("[MongoBookOperation][{}] succeed saving book={}", book.isbn, savedBook.id)

        return true
    }

    override fun edit(book: Book): Boolean {
        val documentBook = mongoBookRepository.findByIsbn(book.isbn)
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없어요.")

        documentBook.category = DocumentBookMapper.map(book.category)
        val updatedDocumentBook = mongoBookRepository.save(documentBook)
        log.info("[MongoBookOperation][{}] succeed updating book={}", book.isbn, updatedDocumentBook.id)

        return true
    }
}