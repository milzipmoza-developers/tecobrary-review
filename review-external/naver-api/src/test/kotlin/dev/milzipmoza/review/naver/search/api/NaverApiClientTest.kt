package dev.milzipmoza.review.naver.search.api

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NaverApiClientTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var naverApiClient: NaverApiClient

    @Test
    internal fun name() {
        val response = naverApiClient.searchBooks("객체지향", 10, 1)

        logger.info("response={}", response)
    }
}