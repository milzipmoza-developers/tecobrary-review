package dev.milzipmoza.review.api.endpoint.authentication.auth

import java.time.Duration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class MemberAuthControllerTest {

    @MockBean
    private lateinit var memberAuthService: MemberAuthService

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    internal fun setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMinutes(60))
                .build()
    }

    @DisplayName("deviceId 헤더가 없으면 에러가 발생한다.")
    @Test
    fun isBadRequest() {
        webTestClient.get()
                .uri { builder -> builder.path("/api/authenticates")
                        .queryParam("code", "a")
                        .build() }
                .exchange()
                .expectStatus().isBadRequest
    }

    @DisplayName("deviceId 헤더가 있으면 성공적으로 응답을 수신한다.")
    @Test
    fun isSuccessful() {
        val token = "token"

        given(memberAuthService.createToken(anyString(), anyString()))
                .willReturn(token)

        webTestClient.get()
                .uri { builder -> builder.path("/api/authenticates")
                        .queryParam("code", "a")
                        .build() }
                .header("X-TECOBRARY-DEVICE-ID", "a")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("data.token").isEqualTo(token)
                .jsonPath("data.issuedDate").isNotEmpty
    }
}
