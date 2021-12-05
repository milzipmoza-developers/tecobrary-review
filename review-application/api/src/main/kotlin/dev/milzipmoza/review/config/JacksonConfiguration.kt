package dev.milzipmoza.review.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class JacksonConfiguration {

    @Bean
    fun mappingJackson2HttpMessageConverter(objectMapper: ObjectMapper): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter(objectMapper)
    }

    @Bean
    fun objectMapper() = jsonMapper {
        addModule(kotlinModule())
        addModule(JavaTimeModule())
        enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }
}