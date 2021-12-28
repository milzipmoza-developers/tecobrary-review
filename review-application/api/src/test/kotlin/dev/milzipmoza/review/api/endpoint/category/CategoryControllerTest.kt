package dev.milzipmoza.review.api.endpoint.category

import dev.milzipmoza.review.api.ApiCreateBody
import dev.milzipmoza.review.api.ApiUpdateBody
import dev.milzipmoza.review.api.endpoint.category.action.CreateCategoryDto
import dev.milzipmoza.review.api.endpoint.category.action.UpdateCategoryDto
import dev.milzipmoza.review.mongo.category.mongo.DocumentCategory
import dev.milzipmoza.review.mongo.category.mongo.DocumentCategoryImage
import dev.milzipmoza.review.mongo.category.mongo.MongoCategoryRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CategoryControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var mongoCategoryRepository: MongoCategoryRepository

    private lateinit var documentCategoryNo: String
    private lateinit var updateCategoryNo: String

    @BeforeEach
    internal fun setUp() {
        mongoCategoryRepository.deleteAll()

        documentCategoryNo = mongoCategoryRepository.insert(DocumentCategory(
                name = "리액트",
                description = "META 에서 개발한 라이브러리",
                image = DocumentCategoryImage(
                        host = "https://tecobrary-pivot.s3.ap-northeast-2.amazonaws.com/",
                        path = "/logos/react_logo.png"
                )
        )).id.toHexString()

        mongoCategoryRepository.insert(DocumentCategory(
                name = "스프링부트",
                description = "한국에서 가장 많이 쓰이는 프레임워크",
                image = DocumentCategoryImage(
                        host = "https://tecobrary-pivot.s3.ap-northeast-2.amazonaws.com/",
                        path = "/logos/react_logo.png"
                )
        ))

        updateCategoryNo = mongoCategoryRepository.insert(DocumentCategory(
                name = "코틀린",
                description = "새로운 jvm 언어 !",
                image = DocumentCategoryImage(
                        host = "https://tecobrary-pivot.s3.ap-northeast-2.amazonaws.com/",
                        path = "/logos/react_logo.png"
                )
        )).id.toHexString()
    }

    @Test
    fun doCreate() {
        webTestClient.post()
                .uri("/api/categories")
                .body(BodyInserters.fromValue(
                        ApiCreateBody(
                                create = CreateCategoryDto(
                                        name = "스프링",
                                        description = "자바의 웹 프레임워크",
                                        imageUrl = "https://tecobrary-pivot.s3.ap-northeast-2.amazonaws.com/logos/springboot_logo.png"
                                )
                        )
                ))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("data").isNotEmpty
    }

    @Test
    fun get() {
        webTestClient.get()
                .uri("/api/categories/{categoryNo}", documentCategoryNo)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("status").isEqualTo("SUCCESS")
                .jsonPath("message").isNotEmpty
                .jsonPath("responseDateTime").isNotEmpty
                .jsonPath("data").isNotEmpty
                .jsonPath("data.no").isEqualTo(documentCategoryNo)
                .jsonPath("data.name").isNotEmpty
                .jsonPath("data.description").isNotEmpty
                .jsonPath("data.imageUrl").isNotEmpty
    }

    @Test
    fun getCategories() {
        webTestClient.get()
                .uri { it.path("/api/categories")
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
                .jsonPath("data.total").isEqualTo(3)
                .jsonPath("data.size").isNumber
                .jsonPath("data.size").isEqualTo(2)
                .jsonPath("data.isFirst").isBoolean
                .jsonPath("data.isFirst").isEqualTo(true)
                .jsonPath("data.isLast").isBoolean
                .jsonPath("data.isLast").isEqualTo(false)
                .jsonPath("data.items").isArray
    }

    @Test
    fun doUpdate() {
        webTestClient.post()
                .uri("/api/categories/{categoryNo}", updateCategoryNo)
                .body(BodyInserters.fromValue(
                        ApiUpdateBody(
                                update = UpdateCategoryDto(
                                        description = "자바의 웹 프레임워크",
                                        imageUrl = "https://tecobrary-pivot.s3.ap-northeast-2.amazonaws.com/logos/springboot_logo.png"
                                )
                        )
                ))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("data").isNotEmpty
    }
}