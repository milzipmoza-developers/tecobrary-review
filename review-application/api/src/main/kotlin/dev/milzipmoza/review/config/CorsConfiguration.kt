package dev.milzipmoza.review.config

import dev.milzipmoza.review.config.filter.CorsFilter
import dev.milzipmoza.review.config.filter.JwtAuthenticationFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration {

    @Bean
    fun filterRegistration(corsFilter: CorsFilter) =
            FilterRegistrationBean<CorsFilter>()
                    .apply {
                        this.filter = corsFilter
                        addUrlPatterns("/*")
                    }
}