package dev.milzipmoza.review.api.endpoint.search.limit

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl
import dev.milzipmoza.review.domain.search.model.SearchBook
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookMapper
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import dev.milzipmoza.review.slack.send.SlackMessageSender
import dev.milzipmoza.review.utils.MessageContentUtils
import org.springframework.scheduling.annotation.Async

@ApplicationService
class SearchResultProcessService(
        private val mongoBookRepository: MongoBookRepository,
        private val slackMessageSender: SlackMessageSender
) {

    @Async
    fun process(items: List<SearchBook>) {
        val searchIsbns = items.map { it.isbn }.toSet()

        val foundBooks = mongoBookRepository.findAllByIsbnIn(searchIsbns)

        val notSavedBooks = searchIsbns subtract foundBooks.map { it.isbn }.toSet()

        val toSaveBooks = items.filter { notSavedBooks.contains(it.isbn) }
                .map {
                    Book(
                            isbn = it.isbn,
                            detail = BookDetail(
                                    image = BookImageUrl(it.imageUrl),
                                    title = it.title,
                                    publisher = it.publisher,
                                    author = it.author,
                                    publishDate = it.publishDate,
                                    description = it.description
                            )
                    )
                }
                .map { DocumentBookMapper.map(it) }
                .toList()

        mongoBookRepository.saveAll(toSaveBooks)
        val message = MessageContentUtils.multipleBooks(toSaveBooks)
        slackMessageSender.send(message)
    }
}