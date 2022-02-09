package dev.milzipmoza.review.config

import dev.milzipmoza.review.config.filter.AdminAuthenticationFilter
import dev.milzipmoza.review.config.filter.JwtAuthenticationFilter
import dev.milzipmoza.review.config.filter.OptionalJwtAuthenticationFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfiguration {

    @Bean
    fun adminFilterRegistration(adminAuthenticationFilter: AdminAuthenticationFilter) =
            FilterRegistrationBean<AdminAuthenticationFilter>()
                    .apply {
                        this.filter = adminAuthenticationFilter
                        addUrlPatterns("/api/books/*")
                        addUrlPatterns("/api/categories/*")
                        addUrlPatterns("/api/tags/*")
                    }

    @Bean
    fun jwtFilterRegistration(jwtAuthenticationFilter: JwtAuthenticationFilter) =
            FilterRegistrationBean<JwtAuthenticationFilter>()
                    .apply {
                        this.filter = jwtAuthenticationFilter
                        addUrlPatterns("/api/members/*")
                        addUrlPatterns("/api/authentications/user-infos")
                    }

    @Bean
    fun optionalJwtFilterRegistration(optionalJwtAuthenticationFilter: OptionalJwtAuthenticationFilter) =
            FilterRegistrationBean<OptionalJwtAuthenticationFilter>()
                    .apply {
                        this.filter = optionalJwtAuthenticationFilter
                        addUrlPatterns("/api/displays/books/*")
                        addUrlPatterns("/api/reviews/*")
                        addUrlPatterns("/api/marks/*")
                    }
}