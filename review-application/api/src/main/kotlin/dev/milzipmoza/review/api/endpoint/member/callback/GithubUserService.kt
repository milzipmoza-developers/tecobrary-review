package dev.milzipmoza.review.api.endpoint.member.callback

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.github.api.GithubApiModule
import dev.milzipmoza.review.github.api.GithubUserDto
import dev.milzipmoza.review.github.login.GithubAccessTokenModule

@ApplicationService
class GithubUserService(
        private val githubAccessTokenModule: GithubAccessTokenModule,
        private val githubApiModule: GithubApiModule
) {

    fun getBy(githubCode: String): GithubUserDto {
        val accessToken = githubAccessTokenModule.getBy(githubCode)
        return githubApiModule.getBy(accessToken)
    }
}