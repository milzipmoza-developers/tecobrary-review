package dev.milzipmoza.review.api.endpoint.authentication.callback

data class CallbackRequest(
        val code: String,
        val state: String
) {
    val deviceId = state
}