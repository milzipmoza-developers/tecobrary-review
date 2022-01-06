package dev.milzipmoza.review.github.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GithubApiProperties::class)
class GithubApiConfiguration