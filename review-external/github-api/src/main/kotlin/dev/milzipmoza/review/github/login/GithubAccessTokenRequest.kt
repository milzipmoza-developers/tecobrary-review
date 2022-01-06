package dev.milzipmoza.review.github.login

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubAccessTokenRequest(
        @JsonProperty("client_id")
        val clientId: String,
        @JsonProperty("client_secret")
        val clientSecret: String,
        val code: String
)