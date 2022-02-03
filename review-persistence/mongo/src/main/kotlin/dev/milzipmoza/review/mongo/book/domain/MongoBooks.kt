package dev.milzipmoza.review.mongo.book.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.book.mongo.DocumentBook
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookMapper
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import dev.milzipmoza.review.mongo.extension.PageRequest
import java.time.LocalDate
import org.springframework.stereotype.Repository

@Repository
class MongoBooks(
        private val mongoBookRepository: MongoBookRepository
) : Books {

    override fun findBy(isbn: String): Book {
        val documentBook = mongoBookRepository.findByIsbn(isbn)
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없어요.")

        return DocumentBookMapper.map(documentBook)
    }

    override fun findBy(pageQuery: PageQuery): PageEntities<Book> {
        val documentBooks = mongoBookRepository.findAll(PageRequest.of(pageQuery))

        return PageEntities(
                total = documentBooks.totalElements,
                size = documentBooks.size,
                isFirst = documentBooks.isFirst,
                isLast = documentBooks.isLast,
                items = documentBooks.content
                        .map { DocumentBookMapper.map(it) }
                        .toList())
    }

    override fun findAllBy(categoryNo: String, pageQuery: PageQuery): PageEntities<Book> {
        val documentBooks = mongoBookRepository.findAllByCategoryNo(categoryNo, PageRequest.of(pageQuery))

        return PageEntities(
                total = documentBooks.totalElements,
                size = documentBooks.size,
                isFirst = documentBooks.isFirst,
                isLast = documentBooks.isLast,
                items = documentBooks.content
                        .map { DocumentBookMapper.map(it) }
                        .toList())
    }

    override fun findAllIn(isbns: List<String>): List<Book> {
        val documentBooks: List<DocumentBook> = mongoBookRepository.findAllByIsbnIn(isbns)

        return DocumentBookMapper.map(documentBooks)
    }

    override fun getRecentPublished(recentMonths: Long, count: Int): List<Book> {
        val documentBooks = mongoBookRepository.findAllAfterPublishDate(LocalDate.now().minusMonths(recentMonths), count)

        return DocumentBookMapper.map(documentBooks)
    }
}