package dev.milzipmoza.review.github.api

data class GithubUserDto(
        val id: String,
        val name: String,
        val avatarUrl: String,
        val email: String,
        val bio: String
)