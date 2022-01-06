package dev.milzipmoza.review.api.endpoint.member

data class CallbackRequest(
        val code: String,
        val state: String
)