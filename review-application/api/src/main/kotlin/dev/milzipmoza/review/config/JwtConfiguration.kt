package dev.milzipmoza.review.config

import dev.milzipmoza.review.config.filter.JwtAuthenticationFilter
import dev.milzipmoza.review.config.filter.OptionalJwtAuthenticationFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfiguration {

    @Bean
    fun jwtFilterRegistration(jwtAuthenticationFilter: JwtAuthenticationFilter) =
            FilterRegistrationBean<JwtAuthenticationFilter>()
                    .apply {
                        this.filter = jwtAuthenticationFilter
                        addUrlPatterns("/api/members/*")
                        addUrlPatterns("/api/authentications/user-infos")
                        addUrlPatterns("/api/marks/*")
                    }

    @Bean
    fun optionalJwtFilterRegistration(optionalJwtAuthenticationFilter: OptionalJwtAuthenticationFilter) =
            FilterRegistrationBean<OptionalJwtAuthenticationFilter>()
                    .apply {
                        this.filter = optionalJwtAuthenticationFilter
                        addUrlPatterns("/api/displays/books/*")
                        addUrlPatterns("/api/reviews/*")
                    }
}