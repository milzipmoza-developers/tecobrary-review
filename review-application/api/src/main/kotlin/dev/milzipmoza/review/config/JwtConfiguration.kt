package dev.milzipmoza.review.config

import dev.milzipmoza.review.config.filter.JwtAuthenticationFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfiguration {

    @Bean
    fun filterRegistration(jwtAuthenticationFilter: JwtAuthenticationFilter) =
            FilterRegistrationBean<JwtAuthenticationFilter>()
                    .apply {
                        this.filter = jwtAuthenticationFilter
                        addUrlPatterns("/api/members/*")
                        addUrlPatterns("/api/authentications/user-infos")
                    }
}