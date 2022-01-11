package dev.milzipmoza.review.github.api

object GithubApiHeaderFactory {

    fun create(accessToken: String) = "token $accessToken"
}