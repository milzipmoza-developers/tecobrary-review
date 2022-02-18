package dev.milzipmoza.review.slack.api

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class SlackApiClientTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var slackApiClient: SlackApiClient

    @Disabled
    @Test
    internal fun send() {
        val body = SlackApiMessageBody("테스트 메시지")
        val response = slackApiClient.sendMessage(
                "a",
                "a",
                "a",
                body
        )

        logger.info("response={}", response)
    }
}