package dev.milzipmoza.review.api.endpoint.category

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CategoryControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun create() {
        webTestClient.post()
                .uri("/api/categories")
                .body(BodyInserters.fromValue(
                        CreateCategoryRequest(
                                colorCode = "#000000",
                                name = "스프링",
                                description = "자바의 웹 프레임워크",
                                imageUrl = "https://tecobrary-pivot.s3.ap-northeast-2.amazonaws.com/logos/springboot_logo.png"
                        )
                ))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("data").isNotEmpty
    }
}