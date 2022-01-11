package dev.milzipmoza.review.api.endpoint.member.callback

data class CallbackRequest(
        val code: String,
        val state: String
) {
    val deviceId = state
}