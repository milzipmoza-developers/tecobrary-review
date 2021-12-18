package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.api.ApiCreateBody
import dev.milzipmoza.review.api.endpoint.category.CreateCategoryDto
import java.time.LocalDate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CreateBookControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun requestCreateBook() {
        webTestClient.post()
                .uri("/api/books")
                .body(BodyInserters.fromValue(
                        ApiCreateBody(
                                create = CreateBookDto(
                                        isbn = "9791156643197",
                                        imageUrl = "https://bookthumb-phinf.pstatic.net/cover/091/459/09145968.jpg?type=m1&udate=20211002",
                                        title = "객체지향의 사실과 오해 (역할, 책임, 협력 관점에서 본 객체지향)",
                                        publisher = "위키북스",
                                        author = "조영호",
                                        publishDate = LocalDate.of(2017, 4, 10),
                                        description = """
                                            객체지향에 대한 선입견을 버려라!
                                            『객체지향의 사실과 오해』는 객체지향이란 무엇인가라는 원론적면서도 다소 위험한 질문에 답하기 위해 쓰여진 책이다.
                                            안타깝게도 많은 사람들이 객체지향의 본질을 오해하고 있다.
                                            가장 널리 퍼져있는 오해는 클래스가 객체지향 프로그래밍의 중심이라는 것이다....
                                        """.trimIndent()
                                )
                        )
                ))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("data").isBoolean
    }
}