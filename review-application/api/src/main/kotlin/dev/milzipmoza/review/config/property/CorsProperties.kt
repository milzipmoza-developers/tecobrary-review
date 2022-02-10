package dev.milzipmoza.review.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "api.cors")
data class CorsProperties(
        var allowOrigin: String = ""
)