package dev.milzipmoza.review.naver.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "external.naver-api")
data class NaverApiProperties(
        var id: String = "",
        var secret: String = ""
)