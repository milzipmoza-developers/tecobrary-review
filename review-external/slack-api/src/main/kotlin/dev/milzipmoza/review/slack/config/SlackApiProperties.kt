package dev.milzipmoza.review.slack.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "external.slack-api")
class SlackApiProperties(
        var projectId: String = "",
        var channelId: String = "",
        var senderId: String = ""
)