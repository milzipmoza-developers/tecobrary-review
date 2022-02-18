package dev.milzipmoza.review.slack.send

import dev.milzipmoza.review.slack.api.SlackApiClient
import dev.milzipmoza.review.slack.api.SlackApiMessageBody
import dev.milzipmoza.review.slack.config.SlackApiProperties
import org.springframework.stereotype.Component

@Component
class SlackMessageSender(
        private val properties: SlackApiProperties,
        private val slackApiClient: SlackApiClient
) {

    fun send(message: String): Boolean {
        val body = SlackApiMessageBody(message)

        val response = slackApiClient.sendMessage(
                projectId = properties.projectId,
                channelId = properties.channelId,
                senderId = properties.senderId,
                body = body
        )

        return "OK" == response
    }
}