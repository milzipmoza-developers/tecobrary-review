package dev.milzipmoza.review.github.api

import org.springframework.stereotype.Component

@Component
class GithubApiModule(
        private val githubApiClient: GithubApiClient
) {

    fun getBy(accessToken: String): GithubUserDto {
        val token = GithubApiHeaderFactory.create(accessToken)
        val userResponse = githubApiClient.getUser(token)
        val emailsResponse = githubApiClient.getEmails(token)
        val email = emailsResponse.filter { it.primary && it.verified }
                .find { it.visibility != null }
                ?.email
                ?: throw IllegalArgumentException("회원가입이 불가능한 계정입니다.")

        return GithubUserDto(
                id = userResponse.id,
                name = userResponse.login,
                avatarUrl = userResponse.avatarUrl,
                email = email,
                bio = userResponse.bio
        )
    }
}