package dev.milzipmoza.review.github.login

import dev.milzipmoza.review.github.config.GithubApiProperties
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GithubAccessTokenModule(
        private val githubLoginClient: GithubLoginClient,
        private val githubApiProperties: GithubApiProperties
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getBy(code: String): String {
        val requestBody = GithubAccessTokenRequest(
                clientId = githubApiProperties.clientId,
                clientSecret = githubApiProperties.clientSecret,
                code = code
        )
        val response = githubLoginClient.getAccessToken(requestBody)
        logger.info("github api response={}", response)
        return response.accessToken
    }
}