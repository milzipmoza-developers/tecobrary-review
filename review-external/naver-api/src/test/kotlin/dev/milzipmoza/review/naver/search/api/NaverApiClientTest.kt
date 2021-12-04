package dev.milzipmoza.review.naver.search.api

import org.assertj.core.api.Assertions.assertThat
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

        assertThat(response.total).isNotNull
        assertThat(response.start).isNotNull
        assertThat(response.display).isNotNull
        assertThat(response.lastBuildDate).isNotNull
        assertThat(response.items).isNotNull
    }

    @Test
    internal fun name2() {
        val response = naverApiClient.searchBooks("절차지향", 10, 1)

        logger.info("response={}", response)

        assertThat(response.total).isNotNull
        assertThat(response.start).isNotNull
        assertThat(response.display).isNotNull
        assertThat(response.lastBuildDate).isNotNull
        assertThat(response.items).isNotNull
    }
}