package dev.milzipmoza.review.api.endpoint.tag.action.create

import dev.milzipmoza.review.api.ApiCreateBody
import dev.milzipmoza.review.api.endpoint.tag.action.create.CreateTagDto
import java.time.Duration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CreateTagControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    internal fun setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMinutes(60))
                .build()
    }

    @Test
    fun requestCreateTag() {
        webTestClient.put()
                .uri("/api/tags")
                .body(BodyInserters.fromValue(
                        ApiCreateBody(
                                create = CreateTagDto(
                                        colorCode = "#000000",
                                        name = "객체지향",
                                        description = "객체지향 패러다임"
                                )
                        )
                ))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("data").isNotEmpty
    }
}
