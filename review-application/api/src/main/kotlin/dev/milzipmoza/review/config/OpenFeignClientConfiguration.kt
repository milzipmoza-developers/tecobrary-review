package dev.milzipmoza.review.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = [
    "dev.milzipmoza.review.naver",
    "dev.milzipmoza.review.github",
])
class OpenFeignClientConfiguration