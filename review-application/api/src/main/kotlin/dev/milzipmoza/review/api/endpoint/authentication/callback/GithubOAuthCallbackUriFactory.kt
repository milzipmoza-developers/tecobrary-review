package dev.milzipmoza.review.api.endpoint.authentication.callback

import dev.milzipmoza.review.api.endpoint.authentication.UrlStatus
import java.net.URI
import org.springframework.web.util.UriComponentsBuilder

object GithubOAuthCallbackUriFactory {

    private val uri = URI("http://localhost:3000")

    fun success(action: String, code: String, status: UrlStatus): String {
        return UriComponentsBuilder.fromUri(uri)
                .queryParam("status", status)
                .queryParam("action", action)
                .queryParam("code", code)
                .build()
                .toUriString()
    }

    fun failed(action: String, status: UrlStatus): String {
        return UriComponentsBuilder.fromUri(uri)
                .queryParam("status", status)
                .queryParam("action", action)
                .build()
                .toUriString()
    }
}