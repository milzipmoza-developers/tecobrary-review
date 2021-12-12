package dev.milzipmoza.review.api.endpoint.marks.search

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class AllMarkSearchControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun requestUsingPage() {
        webTestClient.get()
                .uri {
                    it.path("/api/marks")
                            .queryParam("page", 1)
                            .queryParam("size", 10)
                            .build()
                }
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("status").isEqualTo("SUCCESS")
                .jsonPath("message").isNotEmpty
                .jsonPath("responseDateTime").isNotEmpty
                .jsonPath("data").isNotEmpty
                .jsonPath("data.total").isNumber
                .jsonPath("data.size").isNumber
                .jsonPath("data.isFirst").isBoolean
                .jsonPath("data.isLast").isBoolean
                .jsonPath("data.items").isArray
    }

    @Test
    fun requestUsingPageAndMemberNo() {
        webTestClient.get()
                .uri {
                    it.path("/api/marks")
                            .queryParam("page", 1)
                            .queryParam("size", 10)
                            .queryParam("memberNo", "10")
                            .build()
                }
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("status").isEqualTo("SUCCESS")
                .jsonPath("message").isNotEmpty
                .jsonPath("responseDateTime").isNotEmpty
                .jsonPath("data").isNotEmpty
                .jsonPath("data.total").isNumber
                .jsonPath("data.size").isNumber
                .jsonPath("data.isFirst").isBoolean
                .jsonPath("data.isLast").isBoolean
                .jsonPath("data.items").isArray
    }

    @Test
    fun requestUsingPageAndBookNo() {
        webTestClient.get()
                .uri {
                    it.path("/api/marks")
                            .queryParam("page", 1)
                            .queryParam("size", 10)
                            .queryParam("bookNo", "10")
                            .build()
                }
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("status").isEqualTo("SUCCESS")
                .jsonPath("message").isNotEmpty
                .jsonPath("responseDateTime").isNotEmpty
                .jsonPath("data").isNotEmpty
                .jsonPath("data.total").isNumber
                .jsonPath("data.size").isNumber
                .jsonPath("data.isFirst").isBoolean
                .jsonPath("data.isLast").isBoolean
                .jsonPath("data.items").isArray
    }

    @Test
    fun requestUsingPageAndMemberNoAndBookNo() {
        webTestClient.get()
                .uri {
                    it.path("/api/marks")
                            .queryParam("page", 1)
                            .queryParam("size", 10)
                            .queryParam("memberNo", "10")
                            .queryParam("bookNo", "10")
                            .build()
                }
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("status").isEqualTo("SUCCESS")
                .jsonPath("message").isNotEmpty
                .jsonPath("responseDateTime").isNotEmpty
                .jsonPath("data").isNotEmpty
                .jsonPath("data.total").isNumber
                .jsonPath("data.size").isNumber
                .jsonPath("data.isFirst").isBoolean
                .jsonPath("data.isLast").isBoolean
                .jsonPath("data.items").isArray
    }
}