package dev.milzipmoza.review.github.oauth

import dev.milzipmoza.review.github.config.GithubApiProperties
import java.net.URI
import java.util.*
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class GithubLoginUrlGenerator(
        private val githubApiProperties: GithubApiProperties
) {

    fun generate(): String {
        val uri = URI("https://github.com/login/oauth/authorize")
        return UriComponentsBuilder.fromUri(uri)
                .queryParam("client_id", githubApiProperties.clientId)
                .queryParam("redirect_uri", githubApiProperties.redirectUri)
                .queryParam("scope", githubApiProperties.scope)
                .queryParam("state", UUID.randomUUID().toString())
                .toUriString()
    }
}