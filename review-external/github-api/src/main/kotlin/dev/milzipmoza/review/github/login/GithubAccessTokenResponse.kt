package dev.milzipmoza.review.github.login

import com.fasterxml.jackson.annotation.JsonProperty


data class GithubAccessTokenResponse(
        @JsonProperty("access_token")
        val accessToken: String,
        val scope: String,
        @JsonProperty("token_type")
        val tokenType: String
)