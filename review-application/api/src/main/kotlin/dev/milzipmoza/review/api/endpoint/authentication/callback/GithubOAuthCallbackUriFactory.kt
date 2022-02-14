package dev.milzipmoza.review.api.endpoint.authentication.callback

import dev.milzipmoza.review.api.endpoint.authentication.UrlStatus
import org.springframework.stereotype.Component
import java.net.URI
import org.springframework.web.util.UriComponentsBuilder

@Component
class GithubOAuthCallbackUriFactory(
        private val properties: GithubOAuthCallbackProperties
) {

    private val uri = URI(properties.originUrl)

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