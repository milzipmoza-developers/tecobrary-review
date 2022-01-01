package dev.milzipmoza.review.api.endpoint.tag.action.addbook

import dev.milzipmoza.review.api.endpoint.tag.action.addbook.TagAddBookDto
import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import dev.milzipmoza.review.domain.tag.TagOperation
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName
import dev.milzipmoza.review.mongo.book.mongo.DocumentBook
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookDetail
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookDetailImage
import dev.milzipmoza.review.mongo.book.mongo.MongoBookDetailRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import dev.milzipmoza.review.mongo.tag.mongo.MongoTagRepository
import java.time.Duration
import java.time.LocalDate
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class TagAddBookControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var tagOperation: TagOperation

    @Autowired
    private lateinit var mongoTagRepository: MongoTagRepository

    @Autowired
    private lateinit var mongoBookRepository: MongoBookRepository

    @Autowired
    private lateinit var mongoBookDetailRepository: MongoBookDetailRepository

    private lateinit var tagNo: String

    private val isbn = "isbn"

    @BeforeEach
    internal fun setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMinutes(60))
                .build()

        tagOperation.save(
                Tag(
                        color = TagColor(code = "#000011"),
                        name = TagName(name = "객체지향"),
                        description = TagDescription(description = "객체지향 패러다임")
                )
        )

        val detailMappingId = mongoBookDetailRepository.save(DocumentBookDetail(
                title = "클린아키텍처",
                publisher = "위키북스",
                author = "로버트마틴",
                image = DocumentBookDetailImage(
                        host = "https://www.naver.com",
                        path = "/image.png"
                ),
                locale = BookLanguage.KOREAN.toString(),
                publishDate = LocalDate.now(),
                description = "로버트마틴의 역작"
        )).id

        mongoBookRepository.save(DocumentBook(
                isbn = isbn,
                detailMappingId = detailMappingId,
                category = null
        ))

        tagNo = mongoTagRepository.findAll()[0].id.toHexString()
    }

    @AfterEach
    internal fun tearDown() {
        mongoTagRepository.deleteAll()
        mongoBookRepository.deleteAll()
    }

    @Test
    fun requestAddBook() {
        webTestClient.post()
                .uri("/api/tags/{tagNo}/add-books", tagNo)
                .body(BodyInserters.fromValue(
                        TagAddBookDto(
                                books = listOf(isbn)
                        )
                ))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("data").isNotEmpty
    }
}