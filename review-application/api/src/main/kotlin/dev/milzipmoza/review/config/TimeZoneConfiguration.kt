package dev.milzipmoza.review.config

import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.annotation.PostConstruct

@Configuration
class TimeZoneConfiguration {

    private val log = Logger.of(this)

    @PostConstruct
    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
        log.info("timezone={}", TimeZone.getDefault())
    }
}