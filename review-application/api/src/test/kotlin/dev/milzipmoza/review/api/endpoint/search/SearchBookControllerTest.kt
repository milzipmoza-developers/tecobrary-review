package dev.milzipmoza.review.api.endpoint.search

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class SearchBookControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    internal fun searchBooks() {
        webTestClient.get()
                .uri { it.path("/api/search-books")
                        .queryParam("keyword", "객체지향의 사실과 오해")
                        .queryParam("page", 0)
                        .queryParam("size", 2)
                        .build() }
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("status").isEqualTo("SUCCESS")
                .jsonPath("message").isNotEmpty
                .jsonPath("responseDateTime").isNotEmpty
                .jsonPath("data").isNotEmpty
                .jsonPath("data.total").isNumber
                .jsonPath("data.size").isNumber
                .jsonPath("data.size").isEqualTo(2)
                .jsonPath("data.isFirst").isBoolean
                .jsonPath("data.isLast").isBoolean
                .jsonPath("data.items").isArray
    }
}