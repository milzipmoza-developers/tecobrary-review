package dev.milzipmoza.review.github.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "external.github-api")
data class GithubApiProperties(
        var clientId: String = "",
        var clientSecret: String = "",
        var redirectUri: String = "",
        var scope: String = ""
)
