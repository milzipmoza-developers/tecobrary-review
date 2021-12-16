package dev.milzipmoza.review.api.endpoint.tag.action

import dev.milzipmoza.review.api.ApiCreateBody
import dev.milzipmoza.review.api.ApiUpdateBody
import dev.milzipmoza.review.domain.tag.TagOperation
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.book.TagBooks
import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName
import java.time.Duration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UpdateTagControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var tagOperation: TagOperation

    private lateinit var tagNo: String

    @BeforeEach
    internal fun setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMinutes(60))
                .build()

        tagNo = tagOperation.save(
                Tag(
                        color = TagColor(code = "#000011"),
                        name = TagName(name = "객체지향"),
                        description = TagDescription(description = "객체지향 패러다임")
                )
        )
    }

    @Test
    fun requestCreateTag() {
        webTestClient.post()
                .uri("/api/tags/{tagNo}", tagNo)
                .body(BodyInserters.fromValue(
                        ApiUpdateBody(
                                update = UpdateTagDto(
                                        colorCode = "#000022",
                                        description = "객체지향 패러다임이다."
                                )
                        )
                ))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("data").isNotEmpty
    }
}