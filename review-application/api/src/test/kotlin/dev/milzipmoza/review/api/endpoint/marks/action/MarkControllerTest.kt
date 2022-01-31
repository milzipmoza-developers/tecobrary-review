package dev.milzipmoza.review.api.endpoint.marks.action

import dev.milzipmoza.review.api.OptionalAuthMemberDto
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkBook
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkMember
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkRepository
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkedRepository
import java.time.Duration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class MarkControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var mongoMarkRepository: MongoMarkRepository

    @Autowired
    private lateinit var mongoMarkedRepository: MongoMarkedRepository


    @BeforeEach
    internal fun setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMinutes(60))
                .build()
    }

//    @Test
//    fun requestDoMark() {
//        webTestClient.post()
//                .uri("/api/marks/like/mark")
//                .body(BodyInserters.fromValue(
//                        MarkDto(BOOK_NO)
//                ))
//                .attribute(OptionalAuthMemberDto.ATTRIBUTE_NAME, OptionalAuthMemberDto(MEMBER_NO, "1"))
//                .exchange()
//                .expectStatus().is2xxSuccessful
//                .expectBody().jsonPath("data").isBoolean
//
//        val type = MarkType.LIKE.toString()
//
//        val documentMark = mongoMarkRepository.findByMemberAndBookAndType(DocumentMarkMember(MEMBER_NO), DocumentMarkBook(BOOK_NO), type)!!
//
//        assertThat(documentMark.member.no).isEqualTo(MEMBER_NO)
//        assertThat(documentMark.book.no).isEqualTo(BOOK_NO)
//        assertThat(documentMark.type).isEqualTo(type)
//
//        val documentMarked = mongoMarkedRepository.findById(documentMark.markedObjectId).unwrap()!!
//
//        assertThat(documentMarked.marked).isEqualTo(true)
//    }
//
//    @Test
//    fun requestDoUnmark() {
//        webTestClient.post()
//                .uri("/api/marks/like/unmark")
//                .body(BodyInserters.fromValue(
//                        MarkDto(BOOK_NO)
//                ))
//                .attribute(OptionalAuthMemberDto.ATTRIBUTE_NAME, OptionalAuthMemberDto(MEMBER_NO, "1"))
//                .exchange()
//                .expectStatus().is2xxSuccessful
//                .expectBody().jsonPath("data").isBoolean
//
//        val type = MarkType.LIKE.toString()
//
//        val documentMark = mongoMarkRepository.findByMemberAndBookAndType(DocumentMarkMember(MEMBER_NO), DocumentMarkBook(BOOK_NO), type)!!
//
//        assertThat(documentMark.member.no).isEqualTo(MEMBER_NO)
//        assertThat(documentMark.book.no).isEqualTo(BOOK_NO)
//        assertThat(documentMark.type).isEqualTo(type)
//
//        val documentMarked = mongoMarkedRepository.findById(documentMark.markedObjectId).unwrap()!!
//
//        assertThat(documentMarked.marked).isEqualTo(false)
//    }

    @Test
    fun requestDoMarkNotLoggedIn() {
        webTestClient.post()
                .uri("/api/marks/like/mark")
                .body(BodyInserters.fromValue(
                        MarkDto(BOOK_NO)
                ))
                .exchange()
                .expectStatus().isBadRequest
    }

    @Test
    fun requestDoUnmarkNotLoggedIn() {
        webTestClient.post()
                .uri("/api/marks/like/unmark")
                .body(BodyInserters.fromValue(
                        MarkDto(BOOK_NO)
                ))
                .exchange()
                .expectStatus().isBadRequest
    }

    companion object {
        private const val MEMBER_NO = "123456789"
        private const val BOOK_NO = "9781938902"
    }
}