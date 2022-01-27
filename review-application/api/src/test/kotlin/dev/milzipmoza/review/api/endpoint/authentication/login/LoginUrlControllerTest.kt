package dev.milzipmoza.review.api.endpoint.authentication.login

import dev.milzipmoza.review.github.oauth.GithubLoginUrlGenerator
import java.time.Duration
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class LoginUrlControllerTest {

    @MockBean
    private lateinit var githubLoginUrlGenerator: GithubLoginUrlGenerator

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    internal fun setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMinutes(60))
                .build()
    }

    @Test
    fun badRequestWhenHeaderIsEmpty() {
        webTestClient.get()
                .uri("/api/logins")
                .exchange()
                .expectStatus().isBadRequest
    }

    @Test
    fun isSuccess() {
        val url = "https://login.url"

        given(githubLoginUrlGenerator.generate(anyString()))
                .willReturn(url)

        webTestClient.get()
                .uri("/api/logins")
                .header("X-TECOBRARY-DEVICE-ID", "a")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("data").isEqualTo(url)
    }
}