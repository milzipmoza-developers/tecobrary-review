package dev.milzipmoza.review.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "tecobrary.authentication.jwt")
data class JwtProperties(
        var issuer: String = "",
        var subject: String = "",
        var scope: String = "",
        var version: String = "",
        var secretKey: String = ""
)