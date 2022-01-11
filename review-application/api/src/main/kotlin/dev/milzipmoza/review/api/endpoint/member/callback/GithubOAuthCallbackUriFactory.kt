package dev.milzipmoza.review.api.endpoint.member.callback

import java.net.URI
import org.springframework.web.util.UriComponentsBuilder

object GithubOAuthCallbackUriFactory {

    private val uri = URI("http://localhost:3000")

    fun success(action: String, status: String, code: String): String {
        return UriComponentsBuilder.fromUri(uri)
                .queryParam("status", status)
                .queryParam("action", action)
                .queryParam("code", code)
                .build()
                .toUriString()
    }

    fun failed(action: String, status: String, errorCode: String): String {
        return UriComponentsBuilder.fromUri(uri)
                .queryParam("status", status)
                .queryParam("action", action)
                .queryParam("errorCode", errorCode)
                .build()
                .toUriString()
    }
}