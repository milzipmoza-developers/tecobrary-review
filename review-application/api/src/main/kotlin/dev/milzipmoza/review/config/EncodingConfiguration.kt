package dev.milzipmoza.review.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CharacterEncodingFilter


@Configuration
class EncodingConfiguration {

    @Bean
    fun filterRegistration(characterEncodingFilter: CharacterEncodingFilter) =
            FilterRegistrationBean<CharacterEncodingFilter>()
                    .apply {
                        this.filter = characterEncodingFilter
                        addUrlPatterns("/*")
                    }

    @Bean
    fun characterEncodingFilter() =
            OrderedCharacterEncodingFilter()
                    .apply {
                        this.encoding = "UTF-8"
                        setForceEncoding(true)
                    }
}