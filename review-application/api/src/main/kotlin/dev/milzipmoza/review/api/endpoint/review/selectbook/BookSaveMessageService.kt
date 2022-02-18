package dev.milzipmoza.review.api.endpoint.review.selectbook

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.mongo.extension.Logger
import dev.milzipmoza.review.slack.send.SlackMessageSender
import org.springframework.scheduling.annotation.Async

@ApplicationService
class BookSaveMessageService(
        private val slackMessageSender: SlackMessageSender
) {
    private val log = Logger.of(this)

    @Async
    fun send(book: Book) {
        val message = """
            [도서 등록 알림]
            * ISBN - ${book.isbn}
            * 제목 - ${book.detail.title}
            
            리뷰 도서 선택시 등록되었습니다.
        """.trimIndent()
        val sendResult = slackMessageSender.send(message)
        log.info("[SelectReviewBookSaveMessageService] sendResult={}", sendResult)
    }
}