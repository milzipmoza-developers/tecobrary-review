package dev.milzipmoza.review.slack.api

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
        value = "slack-api",
        url = "https://hooks.slack.com/"
)
@Component
interface SlackApiClient {

    @PostMapping("/services/{projectId}/{channelId}/{senderId}")
    fun sendMessage(@PathVariable("projectId") projectId: String,
                    @PathVariable("channelId") channelId: String,
                    @PathVariable("senderId") senderId: String,
                    @RequestBody body: SlackApiMessageBody): String
}