package dev.milzipmoza.review.github.oauth

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class GithubLoginUrlGeneratorTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var githubLoginUrlGenerator: GithubLoginUrlGenerator

    @Test
    fun generate() {
        val generate = githubLoginUrlGenerator.generate("deviceId")

        logger.info("uri={}", generate)

        assertThat(generate.startsWith("https://github.com/login/oauth/authorize")).isTrue
    }
}