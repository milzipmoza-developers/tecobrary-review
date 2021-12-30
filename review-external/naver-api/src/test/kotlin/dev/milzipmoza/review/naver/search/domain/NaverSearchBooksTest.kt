package dev.milzipmoza.review.naver.search.domain

import dev.milzipmoza.review.domain.PageQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NaverSearchBooksTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var naverSearchBooks: NaverSearchBooks

    @Test
    fun name() {
        val pageEntities = naverSearchBooks.findAllBy("객체지향의 사실과 오해", PageQuery(0, 10))

        logger.info("pageEntities={}", pageEntities)

        assertThat(pageEntities.items).isNotEmpty
    }
}