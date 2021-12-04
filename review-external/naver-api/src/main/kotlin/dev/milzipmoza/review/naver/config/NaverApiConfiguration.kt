package dev.milzipmoza.review.naver.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import feign.RequestInterceptor
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter


@Configuration
@EnableConfigurationProperties(NaverApiProperties::class)
class NaverApiConfiguration {

    @Bean
    fun authenticationHeaders(naverApiProperties: NaverApiProperties): RequestInterceptor {
        return RequestInterceptor { template ->
            template.header(HEADER_ID, naverApiProperties.id)
                    .header(HEADER_SECRET, naverApiProperties.secret)
        }
    }

    @Bean
    fun mappingJackson2HttpMessageConverter(objectMapper: ObjectMapper): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter(objectMapper)
    }

    @Bean
    fun objectMapper() = jsonMapper {
        addModule(kotlinModule())
        enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }

    companion object {
        private const val HEADER_ID = "X-Naver-Client-Id"
        private const val HEADER_SECRET = "X-Naver-Client-Secret"
    }
}