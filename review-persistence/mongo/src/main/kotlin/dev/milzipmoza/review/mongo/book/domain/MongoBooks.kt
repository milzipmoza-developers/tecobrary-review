package dev.milzipmoza.review.mongo.book.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.book.mongo.DocumentBook
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookMapper
import dev.milzipmoza.review.mongo.book.mongo.MongoBookDetailRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookTagsRepository
import dev.milzipmoza.review.mongo.extension.PageRequest
import java.time.LocalDate
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository

@Repository
class MongoBooks(
        private val mongoBookRepository: MongoBookRepository,
        private val mongoBookDetailRepository: MongoBookDetailRepository,
        private val mongoBookTagsRepository: MongoBookTagsRepository
) : Books {

    override fun findBy(isbn: String): Book {
        val documentBook = mongoBookRepository.findByIsbn(isbn)
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없어요.")

        val documentBookDetail = mongoBookDetailRepository.findById(documentBook.detailMappingId).unwrap()
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없어요.")

        val documentBookTags = mongoBookTagsRepository.findById(documentBook.tagsMappingId).unwrap()
                ?: throw DocumentNotFoundException("해당하는 도서를 찾을 수 없어요.")

        return DocumentBookMapper.map(documentBook, documentBookDetail, documentBookTags)
    }

    override fun findBy(pageQuery: PageQuery): PageEntities<Book> {
        val documentBooks = mongoBookRepository.findAll(PageRequest.of(pageQuery))

        return pageEntities(documentBooks)
    }

    override fun findAllBy(categoryNo: String, pageQuery: PageQuery): PageEntities<Book> {
        val documentBooks = mongoBookRepository.findAllByCategoryNo(categoryNo, PageRequest.of(pageQuery))

        return pageEntities(documentBooks)
    }

    private fun pageEntities(documentBooks: Page<DocumentBook>): PageEntities<Book> {
        val documentBookDetails = mongoBookDetailRepository.findAllByIdIn(documentBooks.content.map { it.detailMappingId })

        val documentBookTags = mongoBookTagsRepository.findAllByIdIn(documentBooks.content.map { it.tagsMappingId })

        return PageEntities(
                total = documentBooks.totalElements,
                size = documentBooks.size,
                isFirst = documentBooks.isFirst,
                isLast = documentBooks.isLast,
                items = documentBooks.content
                        .map {
                            DocumentBookMapper.map(
                                    documentBook = it,
                                    documentBookDetail = documentBookDetails.find { detail -> detail.id == it.detailMappingId }!!,
                                    documentBookTags = documentBookTags.find { tags -> tags.id == it.tagsMappingId }!!
                            )
                        }
                        .toList())
    }

    override fun getRecentPublished(recentMonths: Long, count: Int): List<Book> {
        val documentBookDetails = mongoBookDetailRepository.findAllAfterPublishDate(LocalDate.now().minusMonths(recentMonths), count)

        val documentBooks = mongoBookRepository.findAllByDetailMappingIdIn(documentBookDetails.map { it.id })

        val documentBookTags = mongoBookTagsRepository.findAllByIdIn(documentBooks.map { it.tagsMappingId })

        return DocumentBookMapper.map(documentBooks, documentBookDetails, documentBookTags)
    }
}