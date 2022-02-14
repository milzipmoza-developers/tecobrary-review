package dev.milzipmoza.review.api.endpoint.authentication.callback

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "tecobrary.authentication.callback")
data class GithubOAuthCallbackProperties(
        var originUrl: String = ""
)